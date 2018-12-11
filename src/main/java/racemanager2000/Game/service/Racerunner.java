package racemanager2000.Game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racemanager2000.Game.model.Car;
import racemanager2000.Game.model.Race;
import racemanager2000.Game.model.Season;
import racemanager2000.Game.repository.CarRepository;
import racemanager2000.Game.repository.RaceRepository;

import java.util.ArrayList;

@Service
public class Racerunner {

    private final CarRepository carRepository;

    private final RaceRepository raceRepository;

    @Autowired
    public Racerunner(CarRepository carRepository, RaceRepository raceRepository) {
        this.carRepository = carRepository;
        this.raceRepository = raceRepository;
    }

    public Race runRace(int racenumber, Season season) {
        Race race = new Race();
        race.setRacename("Race " + racenumber);
        System.out.println(race.getRacename() + " is going to start");
        determineRaceResult(calculateOrderDesc(season.getEntryList()));
        raceRepository.save(race);
        return race;
    }

    private static ArrayList<Car> calculateOrderDesc(ArrayList<Car> entrylist) {

        // Sort list with Collections.sort(), provide a custom Comparator
        entrylist.sort((o2, o1) -> (o1.getCarAbillityOverall()).compareTo(o2.getCarAbillityOverall()));

        return entrylist;
    }

    private void determineRaceResult(ArrayList<Car> orderedList) {
        int position = 1;
        for (Car car : orderedList) {
            car.setPoints(orderedList.size() - position);
            carRepository.save(car);
            int points = orderedList.size() - position;
            System.out.println("At place number " + position + " came in : " + car.getName()
                                       + " with points scored this race: " + points);
            position++;
        }

        System.out.println("\n");
    }
}
