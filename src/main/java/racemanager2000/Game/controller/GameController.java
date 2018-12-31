package racemanager2000.Game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racemanager2000.Game.service.Seasonrunner;

import java.util.Map;

@RestController
public class GameController {

    private Seasonrunner seasonrunner;

    @Autowired
    public GameController(Seasonrunner seasonrunner) {
        this.seasonrunner = seasonrunner;
    }

    @PostMapping("/startgame")
    public String post(@RequestBody Map<String, String> body) {
        seasonrunner.startSeason(body.get("seasonname"),
                               body.get("carname"));

        return "Seizoen " + body.get("seasonname") + " is tot een einde gekomen";
    }



}
