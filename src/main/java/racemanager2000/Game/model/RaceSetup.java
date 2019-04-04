package racemanager2000.Game.model;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class RaceSetup {

    private String racename;

    private Integer seasonname;
}
