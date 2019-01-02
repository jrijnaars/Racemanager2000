package racemanager2000.Game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racemanager2000.Game.model.Season;
import racemanager2000.Game.repository.SeasonRepository;
import racemanager2000.Game.repository.SeasonresultsRepository;
import racemanager2000.Game.service.Seasonrunner;

import java.util.Map;

@RestController
public class GameController {

    private Seasonrunner seasonrunner;

    private SeasonRepository seasonRepository;

    private SeasonresultsRepository seasonresultsRepository;

    @Autowired
    public GameController(Seasonrunner seasonrunner, SeasonRepository seasonRepository, SeasonresultsRepository seasonresultsRepository) {
        this.seasonrunner = seasonrunner;
        this.seasonRepository = seasonRepository;
        this.seasonresultsRepository = seasonresultsRepository;
    }

    @PostMapping("/startseason")
    public Season post(@RequestBody Map<String, String> body) throws Exception {
        seasonrunner.startSeason(body.get("seasonname"),
                               body.get("carname"));
        String seasonname = body.get("seasonname");
        return seasonRepository.getSeasonBySeasonname(seasonname);
    }



}
