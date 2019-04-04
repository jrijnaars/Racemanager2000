package racemanager2000.Game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racemanager2000.Game.model.*;
import racemanager2000.Game.repository.CarRepository;
import racemanager2000.Game.repository.RaceRepository;
import racemanager2000.Game.repository.RaceresultsRepository;
import racemanager2000.Game.repository.SeasonRepository;

import java.util.List;

@Service
public class Racerunner {

    private final CarRepository carRepository;

    private final RaceRepository raceRepository;

    private final RaceresultsRepository raceresultsRepository;

    private final SeasonRepository seasonRepository;

    @Autowired
    public Racerunner(CarRepository carRepository, RaceRepository raceRepository, RaceresultsRepository raceresultsRepository, SeasonRepository seasonRepository) {
        this.carRepository = carRepository;
        this.raceRepository = raceRepository;
        this.raceresultsRepository = raceresultsRepository;
        this.seasonRepository = seasonRepository;
    }

    public Race runRace(RaceSetup racesetup) throws Exception {
        Season season = seasonRepository.getSeasonBySeasonname(racesetup.getSeasonname());
        if (season == null) {
            throw new Exception("Season doesn't exists");
        }

        Race existingRace = raceRepository.getByRacename(racesetup.getRacename());
        if (existingRace == null) {
            Race race = new Race();
            race.setRacename(racesetup.getRacename());
            raceRepository.save(race);
            // create raceresults
            List<Car> entryList = carRepository.findAllByOrderByCarAbillityOverallDesc();
            calculateRaceresult(race, entryList, season);

            //update season with number of races
            season.setNumberOfRaces(season.getNumberOfRaces() + 1);
            seasonRepository.save(season);
            return race;
        } else {
            throw new Exception("Race already exists");
        }
    }

    private void calculateRaceresult(Race race, List<Car> entryList, Season season) {

        int position = 1;
        for (Car car : entryList) {

            Raceresult raceresult = new Raceresult();
            raceresult.setRaceId(race.getId());
            raceresult.setCarId(car.getId());
            raceresult.setPosition(position);
            raceresult.setSeasonId(season.getId());
            switch (position++) {
                case 1:
                    raceresult.setPoints(20);
                    break;
                case 2:
                    raceresult.setPoints(12);
                    break;
                case 3:
                    raceresult.setPoints(10);
                    break;
                case 4:
                    raceresult.setPoints(8);
                    break;
                case 5:
                    raceresult.setPoints(6);
                    break;
                case 6:
                    raceresult.setPoints(5);
                    break;
                case 7:
                    raceresult.setPoints(4);
                    break;
                case 8:
                    raceresult.setPoints(3);
                    break;
                case 9:
                    raceresult.setPoints(2);
                    break;
                case 10:
                    raceresult.setPoints(1);
                    break;
                default:
                    raceresult.setPoints(0);
                    break;
            }
            raceresultsRepository.save(raceresult);
            carRepository.save(car);
        }
    }
}
