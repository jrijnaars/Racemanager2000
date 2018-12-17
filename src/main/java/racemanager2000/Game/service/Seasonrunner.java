package racemanager2000.Game.service;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racemanager2000.Game.model.Car;
import racemanager2000.Game.model.Race;
import racemanager2000.Game.model.Season;
import racemanager2000.Game.repository.CarRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class Seasonrunner {

    private Racerunner racerunner;

    final private CarRepository carRepository;

    @Autowired
    public Seasonrunner(Racerunner racerunner, CarRepository carRepository) {
        this.racerunner = racerunner;
        this.carRepository = carRepository;
    }

    public void runSeason(String seasonname, int numberOfRaces, String carname) {
        createOwnTeam(carname);
        ArrayList<Race> races = new ArrayList<>();
        Season season = new Season(seasonname);
        int raceNumber = 1;
        while (raceNumber <= numberOfRaces){
            races.add(racerunner.runRace(raceNumber++, season));
        }
        season.setAllRaces(races);
        calculateSeasonResult(season);
    }

    private void calculateSeasonResult(Season season) {
        System.out.println(season.getSeasonname() + " is coming to an end!");

        List<Car> cars = carRepository.findAllByOrderByPointsDesc();
        int position = 1;
        for (Car entry : cars) {
            System.out.println("At place number " + position + " came in : " + entry.getName()
                                       + " with points scored at the end of the season: " + entry.getPoints());
            position++;
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
