package racemanager2000.Game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racemanager2000.Game.model.Season;
import racemanager2000.Game.model.Seasonresult;
import racemanager2000.Game.repository.SeasonRepository;
import racemanager2000.Game.repository.SeasonresultsRepository;
import racemanager2000.Game.service.Seasonrunner;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class SeasonController {

    private Seasonrunner seasonrunner;

    private SeasonRepository seasonRepository;

    private SeasonresultsRepository seasonresultsRepository;

    @Autowired
    public SeasonController(Seasonrunner seasonrunner, SeasonRepository seasonRepository, SeasonresultsRepository seasonresultsRepository) {
        this.seasonrunner = seasonrunner;
        this.seasonRepository = seasonRepository;
        this.seasonresultsRepository = seasonresultsRepository;
    }

    @PostMapping(value = "/startseason")
    public Season postStart(@RequestBody Map<String, String> body) throws Exception {
        seasonrunner.startSeason(body.get("seasonname"),
                               body.get("carname"));
        String seasonname = body.get("seasonname");
        return seasonRepository.getSeasonBySeasonname(seasonname);
    }

    @PostMapping(value = "/finishseason")
    public List<Seasonresult> postFinish(@RequestBody Map<String, String> body) throws Exception {
        String seasonname = body.get("seasonname");
        Season season = seasonRepository.getSeasonBySeasonname(seasonname);
        seasonrunner.finishSeason(season);

        return seasonresultsRepository.findAllBySeasonIdOrderBySeasonPointsDesc(season.getId());
    }
}
