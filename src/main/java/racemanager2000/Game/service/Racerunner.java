package racemanager2000.Game.service;

import org.springframework.stereotype.Service;
import racemanager2000.Game.model.Car;
import racemanager2000.Game.model.Race;
import racemanager2000.Game.model.Season;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

@Service
public class Racerunner {

    public Race runRace(int racenumber, Season season) {
        Race race = new Race();
        race.setRacename("Race " + racenumber);
        System.out.println(race.getRacename() + " is going to start");
        race.setRaceResult(determineRaceResult(calculateOrderDesc(season.getEntryList())));
        return race;
    }

    private static ArrayList<Car> calculateOrderDesc(ArrayList<Car> entrylist) {

        // Sort list with Collections.sort(), provide a custom Comparator
        Collections.sort(entrylist, new Comparator<Car>() {
            public int compare(Car o2,
                               Car o1) {
                return (o1.getCarAbillityOverall()).compareTo(o2.getCarAbillityOverall());
            }
        });

        return entrylist;
    }

    public HashMap<Integer, Car> determineRaceResult(ArrayList<Car> orderedList) {
        HashMap<Integer, Car> endResult = new HashMap<Integer, Car>();
        int position = 1;
        for (Car car : orderedList) {
            endResult.put(position, car);
            car.setPoints(orderedList.size() - position);
            int points = orderedList.size() - position;
            System.out.println("At place number " + position + " came in : " + car.getName()
                                       + " with points scored this race: " + points);
            position++;
        }

        System.out.println("\n");
        return endResult;
    }
}
