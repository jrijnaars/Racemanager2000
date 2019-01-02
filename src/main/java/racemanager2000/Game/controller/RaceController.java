package racemanager2000.Game.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racemanager2000.Game.service.Racerunner;

import java.util.Map;

@RestController
public class RaceController {

    @Autowired
    private Racerunner racerunner;

    public RaceController(Racerunner racerunner) {
        this.racerunner = racerunner;
    }

    @PostMapping("/startrace")
    public String post(@RequestBody Map<String, String> body) {
        racerunner.runRace(body.get("racename"), body.get("seasonname"));

        return "Seizoen " + body.get("seasonname") + " is tot een einde gekomen";
    }

}
