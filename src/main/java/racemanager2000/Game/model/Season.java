package racemanager2000.Game.model;

import javax.persistence.*;

@Entity
public class Season {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private int seasonname;

    @Column
    private int numberOfRaces;

    @Column
    private boolean seasonFinished;

    public Season() {
    }

    public Season(int seasonname) {
        this.seasonname = seasonname;
    }

    public Long getId() {
        return id;
    }

    public int getSeasonname() {
        return seasonname;
    }

    public void setSeasonname(int seasonname) {
        this.seasonname = seasonname;
    }

    public int getNumberOfRaces() {
        return numberOfRaces;
    }

    public void setNumberOfRaces(int numberOfRaces) {
        this.numberOfRaces = numberOfRaces;
    }

    public boolean isSeasonFinished() {
        return seasonFinished;
    }

    public void setSeasonFinished(boolean seasonFinished) {
        this.seasonFinished = seasonFinished;
    }
}