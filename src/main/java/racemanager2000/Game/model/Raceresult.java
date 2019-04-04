package racemanager2000.Game.model;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Accessors(chain = true)
public class Raceresult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private Long raceId;

    @Column
    private int position;

    @Column
    private int points;

    @Column
    private Long carId;

    @Column
    private Long seasonId;
}
