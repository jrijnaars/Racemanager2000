package racemanager2000.Game.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity
public class Season {
    @Id
    private Long id;
    private String seasonname;
    private int numberOfRaces;
    private ArrayList<Race> allRaces;
    private ArrayList<Car> entryList;

    public Season(String seasonname, ArrayList<Car> entryList) {
        this.seasonname = seasonname;
        this.entryList = entryList;
    }

    public String getSeasonname() {
        return seasonname;
    }

    public void setSeasonname(String seasonname) {
        this.seasonname = seasonname;
    }

    public int getNumberOfRaces() {
        return numberOfRaces;
    }

    public void setNumberOfRaces(int numberOfRaces) {
        this.numberOfRaces = numberOfRaces;
    }

    public ArrayList<Race> getAllRaces() {
        return allRaces;
    }

    public void setAllRaces(ArrayList<Race> allRaces) {
        this.allRaces = allRaces;
    }

    public ArrayList<Car> getEntryList() {
        return entryList;
    }

    public void setEntryList(ArrayList<Car> entryList) {
        this.entryList = entryList;
    }
}