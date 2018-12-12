package racemanager2000.Game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racemanager2000.Game.model.Car;
import racemanager2000.Game.model.Race;
import racemanager2000.Game.model.Raceresult;
import racemanager2000.Game.model.Season;
import racemanager2000.Game.repository.CarRepository;
import racemanager2000.Game.repository.RaceRepository;
import racemanager2000.Game.repository.RaceresultsRepository;

import java.util.List;

@Service
public class Racerunner {

    private final CarRepository carRepository;

    private final RaceRepository raceRepository;

    private final RaceresultsRepository raceresultsRepository;

    @Autowired
    public Racerunner(CarRepository carRepository, RaceRepository raceRepository, RaceresultsRepository raceresultsRepository) {
        this.carRepository = carRepository;
        this.raceRepository = raceRepository;
        this.raceresultsRepository = raceresultsRepository;
    }

    Race runRace(int racenumber, Season season) {
        Race race = new Race();
        race.setRacename("Race " + racenumber);
        System.out.println(race.getRacename() + " is going to start");
        raceRepository.save(race);
        List<Car> entryList = carRepository.findAllByOrderByCarAbillityOverallDesc();
        calculateRaceresult(race, entryList);
        printRaceResult(race);
        return race;
    }

    private void printRaceResult(Race race) {
        List<Raceresult> raceresult = raceresultsRepository.findAllByRaceIdOrderByPositionAsc(race.getId());

        for (Raceresult result : raceresult) {
            Car competitor = carRepository.getById(result.getCarId());
            System.out.println("At place number " + result.getPosition() + " came in : " + competitor.getName()
                                       + " with points scored this race: " + result.getPoints());
        }

        System.out.println("\n");
    }

    private void calculateRaceresult(Race race, List<Car> entryList) {

        int position = 1;
        for (Car car : entryList) {

            Raceresult raceresult = new Raceresult();
            raceresult.setRaceId(race.getId());
            raceresult.setCarId(car.getId());
            raceresult.setPosition(position);
            switch (position++) {
                case 1:
                    car.setPoints(20);
                    raceresult.setPoints(20);
                    break;
                case 2:
                    car.setPoints(12);
                    raceresult.setPoints(12);
                    break;
                case 3:
                    car.setPoints(10);
                    raceresult.setPoints(10);
                    break;
                case 4:
                    car.setPoints(8);
                    raceresult.setPoints(8);
                    break;
                case 5:
                    car.setPoints(6);
                    raceresult.setPoints(6);
                    break;
                case 6:
                    car.setPoints(5);
                    raceresult.setPoints(5);
                    break;
                case 7:
                    car.setPoints(4);
                    raceresult.setPoints(4);
                    break;
                case 8:
                    car.setPoints(3);
                    raceresult.setPoints(3);
                    break;
                case 9:
                    car.setPoints(2);
                    raceresult.setPoints(2);
                    break;
                case 10:
                    car.setPoints(1);
                    raceresult.setPoints(1);
                    break;
                default:
                    car.setPoints(0);
                    raceresult.setPoints(0);
                    break;
            }
            raceresultsRepository.save(raceresult);
            carRepository.save(car);
        }
    }
}
