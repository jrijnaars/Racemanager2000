package racemanager2000.Game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("/")
    public String GameController() {
        seasonrunner.runSeason("seizoen 2018", 2, "Juriaan Rijnaars");
        return "vroem";
    }

    @PostMapping("/startgame")
    public String post(@RequestBody Map<String, String> body) {
        seasonrunner.runSeason(body.get("seasonname"),
                               Integer.parseInt(body.get("numberOfRaces")),
                               body.get("carname"));
        return "vroem";
    }

}
