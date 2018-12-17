package racemanager2000.Game.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Season {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String seasonname;

    @Column
    private int numberOfRaces;

    public Season(String seasonname, int numberOfRaces) {
        this.seasonname = seasonname;
        this.numberOfRaces = numberOfRaces;
    }

    public Long getId() {
        return id;
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
}