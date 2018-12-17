package racemanager2000.Game.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Seasonresult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private Long seasonId;

    @Column
    private int seasonPosition;

    @Column
    private Long carId;

    @Column
    private int seasonPoints;


    public Long getId() {
        return id;
    }

    public Long getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(Long seasonId) {
        this.seasonId = seasonId;
    }

    public int getSeasonPosition() {
        return seasonPosition;
    }

    public void setSeasonPosition(int seasonPosition) {
        this.seasonPosition = seasonPosition;
    }

    public int getSeasonPoints() {
        return seasonPoints;
    }

    public void setSeasonPoints(int seasonPoints) {
        this.seasonPoints = seasonPoints;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }
}
