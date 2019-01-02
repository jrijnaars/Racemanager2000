package racemanager2000.Game.model;

import javax.persistence.*;

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

    public Season() {
    }

    public Season(String seasonname) {
        this.seasonname = seasonname;
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