package racemanager2000.Game.service;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racemanager2000.Game.model.Car;
import racemanager2000.Game.model.Raceresult;
import racemanager2000.Game.model.Season;
import racemanager2000.Game.model.Seasonresult;
import racemanager2000.Game.repository.CarRepository;
import racemanager2000.Game.repository.RaceresultsRepository;
import racemanager2000.Game.repository.SeasonRepository;
import racemanager2000.Game.repository.SeasonresultsRepository;

import java.util.List;

@Service
public class Seasonrunner {

    private Racerunner racerunner;

    final private RaceresultsRepository raceresultsRepository;

    final private CarRepository carRepository;

    final private SeasonRepository seasonRepository;

    final private SeasonresultsRepository seasonresultsRepository;

    @Autowired
    public Seasonrunner(Racerunner racerunner, RaceresultsRepository raceresultsRepository, CarRepository carRepository, SeasonRepository seasonRepository, SeasonresultsRepository seasonresultsRepository) {
        this.racerunner = racerunner;
        this.raceresultsRepository = raceresultsRepository;
        this.carRepository = carRepository;
        this.seasonRepository = seasonRepository;
        this.seasonresultsRepository = seasonresultsRepository;
    }

    public Season startSeason(String seasonname, String carname) throws Exception {
        createOwnTeam(carname);
        Season existingSeason = seasonRepository.getSeasonBySeasonname(seasonname);
        if (existingSeason == null) {
            Season season = new Season(seasonname);
            seasonRepository.save(season);
            return season;
        } else {
            throw new Exception("Season already exists");
        }
    }

    public List<Seasonresult> finishSeason(Season season) throws Exception {
        if (season.isSeasonFinished()) {
            throw new Exception("Season is already finished");
        } else if (season.getNumberOfRaces() <= 0) {
            throw new Exception("no races are driven for this season");
        }

        List<Raceresult> raceresults = raceresultsRepository.findAllBySeasonId(season.getId());

        convertRaceresultToSeasonresults(raceresults, season);

        setPositionToSeasonresults(season);

        season.setSeasonFinished(true);

        return seasonresultsRepository.findAllBySeasonIdOrderBySeasonPointsDesc(season.getId());

    }

    private void setPositionToSeasonresults(Season season) {
        List<Seasonresult> result = seasonresultsRepository.findAllBySeasonIdOrderBySeasonPointsDesc(season.getId());
        int position = 1;
        for (Seasonresult seasonresult : result) {
            seasonresult.setSeasonPosition(position++);
            seasonresultsRepository.save(seasonresult);
        }
    }

    private void convertRaceresultToSeasonresults(List<Raceresult> raceresults, Season season) {
        for (Raceresult raceresult: raceresults) {
            Seasonresult seasonresult = seasonresultsRepository.getSeasonresultsByCarIdAndSeasonId(raceresult.getCarId(), season.getId());
            if (seasonresult == null) {
                Seasonresult newseasonresult = new Seasonresult();
                newseasonresult.setSeasonId(season.getId());
                newseasonresult.setCarId(raceresult.getCarId());
                newseasonresult.setSeasonPoints(raceresult.getPoints());
                seasonresultsRepository.save(newseasonresult);
            } else {
                seasonresult.setSeasonPoints(seasonresult.getSeasonPoints() + raceresult.getPoints());
                seasonresultsRepository.save(seasonresult);
            }
        }
    }

    private void createOwnTeam(String carname){
        if (carRepository.getByName(carname) == null){
            Integer chassis = RandomUtils.nextInt(80, 98);
            Integer engine = RandomUtils.nextInt(80, 98);
            Car ownTeam = new Car(carname, chassis, engine);
            ownTeam.setCarAbillityOverall();
            carRepository.save(ownTeam);
        }
    }
}
