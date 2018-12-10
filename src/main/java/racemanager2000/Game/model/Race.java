package racemanager2000.Game.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.HashMap;

@Entity
public class Race {
    @Id
    private Long id;
    private String racename;
    private HashMap<Integer, Car> raceResult;

    public String getRacename() {
        return racename;
    }

    public void setRacename(String racename) {
        this.racename = racename;
    }

    public HashMap<Integer, Car> getRaceResult() {
        return raceResult;
    }

    public void setRaceResult(HashMap<Integer, Car> raceResult) {
        this.raceResult = raceResult;
    }

}
