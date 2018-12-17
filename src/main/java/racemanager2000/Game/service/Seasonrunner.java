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

    public void runSeason(String seasonname, int numberOfRaces, String carname) {
        createOwnTeam(carname);
        Season season = new Season(seasonname, numberOfRaces);
        seasonRepository.save(season);

        int raceNumber = 1;
        while (raceNumber <= numberOfRaces){
            racerunner.runRace(raceNumber++, season);
        }

        calculateSeasonResult(season);
    }

    private void calculateSeasonResult(Season season) {
        System.out.println(season.getSeasonname() + " is coming to an end!");

        List<Raceresult> raceresults = raceresultsRepository.findAllBySeasonId(season.getId());

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

        // TODO: punten berekenen in seasonresults ipv car points
        List<Seasonresult> result = seasonresultsRepository.findAllBySeasonIdOrderBySeasonPointsDesc(season.getId());
        int position = 1;
        for (Seasonresult seasonresult : result) {
            seasonresult.setSeasonPosition(position++);
            seasonresultsRepository.save(seasonresult);
            Car entry = carRepository.getById(seasonresult.getCarId());

            System.out.println("At place number " + seasonresult.getSeasonPosition() + " came in : " + entry.getName()
                                       + " with points scored at the end of the season: " + seasonresult.getSeasonPoints());
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

    private void createContender(String contender) {
        Integer chassis = RandomUtils.nextInt(1, 98);
        Integer engine = RandomUtils.nextInt(1, 98);
        Car randomcar = new Car(contender, chassis, engine);
        randomcar.setCarAbillityOverall();
        carRepository.save(randomcar);
    }
}
