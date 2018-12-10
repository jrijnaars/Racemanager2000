package racemanager2000.Game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racemanager2000.Game.model.Car;
import racemanager2000.Game.model.Race;
import racemanager2000.Game.model.Season;
import racemanager2000.Game.model.Tyres;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

@Service
public class Seasonrunner {

    private Racerunner racerunner;

    @Autowired
    public Seasonrunner(Racerunner racerunner) {
        this.racerunner = racerunner;
    }

    public void runSeason(String seasonname, int numberOfRaces, String carname) {
        Car ownTeam = new Car(carname, 99, 99, new Tyres("SOFT"));
        ArrayList<Car> entryList = createEntryList(ownTeam);
        ArrayList<Race> races = new ArrayList<Race>();
        Season season = new Season(seasonname, entryList);
        int raceNumber = 1;
        while (raceNumber <= numberOfRaces){
            races.add(racerunner.runRace(raceNumber, season));
            numberOfRaces--;
        }
        season.setAllRaces(races);
        calculateSeasonResult(season);
    }

    private void calculateSeasonResult(Season season) {
        System.out.println(season.getSeasonname() + " is coming to an end!");

        // Sort list with Collections.sort(), provide a custom Comparator
        Collections.sort(season.getEntryList(), new Comparator<Car>() {
            public int compare(Car o2,
                               Car o1) {
                return (o1.getPoints()).compareTo(o2.getPoints());
            }
        });

        int position = 1;
        for (Car entry : season.getEntryList()) {
            System.out.println("At place number " + position + " came in : " + entry.getName()
                                       + " with points scored at the end of the season: " + entry.getPoints());
            position++;
        }
    }




    private ArrayList<Car> createEntryList(Car ownCar) {
        ArrayList<Car> entryList = new ArrayList<Car>();
        entryList.add(ownCar);
        entryList.add(new Car("Jarno Zopfi", 8, 8, new Tyres("SOFT")));
        entryList.add(new Car("Mees van de Rijdt", 16, 16, new Tyres("SOFT")));
        entryList.add(new Car("Bira Van Haver", 24, 24, new Tyres("SOFT")));
        entryList.add(new Car("Melvin Ezinga", 32, 32, new Tyres("SOFT")));
        entryList.add(new Car("Rick Bogaards", 40, 40, new Tyres("SOFT")));
        entryList.add(new Car("Laurens Weber", 48, 48, new Tyres("SOFT")));
        entryList.add(new Car("Benjamin van Hees", 56, 56, new Tyres("SOFT")));
        entryList.add(new Car("Mitchell van Dijk", 64, 64, new Tyres("SOFT")));
        entryList.add(new Car("Esmee Kosterman", 72, 72, new Tyres("SOFT")));
        entryList.add(new Car("Max Knapen", 80, 80, new Tyres("SOFT")));
        entryList.add(new Car("Daan Richartz", 88, 88, new Tyres("SOFT")));
        entryList.add(new Car("Dani van Ruiten", 96, 96, new Tyres("SOFT")));
        return entryList;
    }

    private static Car createTeam() {
        System.out.println("Enter your name: ");
        Scanner scanner = new Scanner(System.in);
        String carname = scanner.nextLine();

        return new Car(carname, 99, 99, new Tyres("SOFT"));
    }
}
