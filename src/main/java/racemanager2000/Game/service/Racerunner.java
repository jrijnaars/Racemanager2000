package racemanager2000.Game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racemanager2000.Game.model.Car;
import racemanager2000.Game.model.Race;
import racemanager2000.Game.model.Season;
import racemanager2000.Game.repository.CarRepository;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class Racerunner {

    private final CarRepository carRepository;

    @Autowired
    public Racerunner(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Race runRace(int racenumber, Season season) {
        Race race = new Race();
        race.setRacename("Race " + racenumber);
        System.out.println(race.getRacename() + " is going to start");
        race.setRaceResult(determineRaceResult(calculateOrderDesc(season.getEntryList())));
        return race;
    }

    private static ArrayList<Car> calculateOrderDesc(ArrayList<Car> entrylist) {

        // Sort list with Collections.sort(), provide a custom Comparator
        entrylist.sort((o2, o1) -> (o1.getCarAbillityOverall()).compareTo(o2.getCarAbillityOverall()));

        return entrylist;
    }

    private HashMap<Integer, Car> determineRaceResult(ArrayList<Car> orderedList) {
        HashMap<Integer, Car> endResult = new HashMap<>();
        int position = 1;
        for (Car car : orderedList) {
            endResult.put(position, car);
            car.setPoints(orderedList.size() - position);
            carRepository.save(car);
            int points = orderedList.size() - position;
            System.out.println("At place number " + position + " came in : " + car.getName()
                                       + " with points scored this race: " + points);
            position++;
        }

        System.out.println("\n");
        return endResult;
    }
}
