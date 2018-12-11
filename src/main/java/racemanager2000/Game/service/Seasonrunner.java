package racemanager2000.Game.service;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racemanager2000.Game.model.Car;
import racemanager2000.Game.model.Race;
import racemanager2000.Game.model.Season;
import racemanager2000.Game.repository.CarRepository;

import java.util.ArrayList;
import java.util.Arrays;
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
        Car ownTeam = createOwnTeam(carname);
        ArrayList<Car> entryList = createEntryList(carRepository.getByName(ownTeam.getName()));
        ArrayList<Race> races = new ArrayList<>();
        Season season = new Season(seasonname, entryList);
        int raceNumber = 1;
        while (raceNumber <= numberOfRaces){
            races.add(racerunner.runRace(raceNumber++, season));
        }
        season.setAllRaces(races);
        calculateSeasonResult(season);
    }

    private void calculateSeasonResult(Season season) {
        System.out.println(season.getSeasonname() + " is coming to an end!");

        List<Car> cars = (List<Car>) carRepository.findAll();
        cars.sort((o2, o1) -> (o1.getPoints()).compareTo(o2.getPoints()));

        int position = 1;
        for (Car entry : cars) {
            System.out.println("At place number " + position + " came in : " + entry.getName()
                                       + " with points scored at the end of the season: " + entry.getPoints());
            position++;
        }
    }

    private ArrayList<Car> createEntryList(Car ownCar) {
        List<String> riders = Arrays.asList("Jarno Zopfi",
                                            "Mees van de Rijdt",
                                            "Bira Van Haver",
                                            "Melvin Ezinga",
                                            "Rick Bogaards",
                                            "Laurens Weber",
                                            "Benjamin van Hees",
                                            "Mitchell van Dijk",
                                            "Esmee Kosterman",
                                            "Max Knapen",
                                            "Daan Richartz",
                                            "Dani van Ruiten"
                                            );

        ArrayList<Car> entrylist = new ArrayList<>();
        entrylist.add(ownCar);

        for (String rider: riders) {
            Integer chassis = RandomUtils.nextInt(1, 98);
            Integer engine = RandomUtils.nextInt(1, 98);
            Car randomcar = new Car(rider, chassis, engine);
            randomcar.setCarAbillityOverall();
            carRepository.save(randomcar);
            entrylist.add(randomcar);
        }
        return entrylist;
    }

    private Car createOwnTeam(String carname){
        try {
            Car ownTeam = new Car(carname, 99, 99);
            ownTeam.setCarAbillityOverall();
            carRepository.save(ownTeam);
            return ownTeam;
        } catch (Exception e) {
            System.out.println("deelnemer " + carname + " bestaat al");
            return carRepository.getByName(carname);
        }
    }
}
