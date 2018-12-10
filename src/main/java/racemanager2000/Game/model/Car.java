package racemanager2000.Game.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Car {
    @Id
    private Long id;
    private String name;
    private int chassis;
    private int engine;
    @OneToOne
    private Tyres tires;
    private Integer points;

    public Car(String name, int chassis, int engine, Tyres tires) {
        if (name == null) {
            throw new IllegalArgumentException("name cannot be null");
        }

        this.tires = tires;
        this.name = name;
        this.chassis = chassis;
        this.engine = engine;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChassis() {
        return chassis;
    }

    public void setChassis(int chassis) {
        this.chassis = chassis;
    }

    public int getEngine() {
        return engine;
    }

    public void setEngine(int engine) {
        this.engine = engine;
    }

    public Tyres getTires() {
        return tires;
    }

    public void setTires(String tires) {
        this.tires = new Tyres(tires);
    }

    public void setPoints(Integer points) {
        if (this.points == null){
            this.points = 0;
        }
        this.points = this.points + points;
    }

    public Integer getPoints() {
        return this.points;
    }

    public Integer getCarAbillityOverall() {
        return (chassis + engine + tires.getAbillity()) / 3;
    }
}
