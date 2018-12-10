package racemanager2000.Game.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Random;

@Entity
public class Tyres {
    @Id
    private Long id;
    private String tyretype;
    private int abillity;

    public Tyres(String tyretype) {
        Random randomInt = new Random();
        if (tyretype == null) {
            throw new IllegalArgumentException("Tyretype is null");
        }
        if (tyretype.equals("SOFT")) {
            this.tyretype = tyretype;
            this.abillity = randomInt.nextInt(99);

        } else if (tyretype.equals("HARD")) {
            this.tyretype = tyretype;
            this.abillity = randomInt.nextInt(50);
        }else {
            throw new IllegalArgumentException("no tyretype filled in");
        }
    }

    public String getTyretype() {
        return tyretype;
    }

    public int getAbillity() {
        return abillity;
    }
}
