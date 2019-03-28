package racemanager2000.Game.model;

import javax.persistence.*;

@Entity (name = "Car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private int chassis;

    @Column
    private int engine;

    @Column
    private Integer carAbillityOverall;

    public Car () {

    }

    public Car(String name, int chassis, int engine) {
        if (name == null) {
            throw new IllegalArgumentException("name cannot be null");
        }
        this.name = name;
        this.chassis = chassis;
        this.engine = engine;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return this.name;
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

    public void setCarAbillityOverall() {
        this.carAbillityOverall = (this.chassis + this.engine) / 2;
    }
}
