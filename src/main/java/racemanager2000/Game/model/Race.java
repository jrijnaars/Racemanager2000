package racemanager2000.Game.model;

import javax.persistence.*;

@Entity
public class Race {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String racename;

    public Long getId() {
        return this.id;
    }

    public String getRacename() {
        return this.racename;
    }

    public void setRacename(String racename) {
        this.racename = racename;
    }
}
