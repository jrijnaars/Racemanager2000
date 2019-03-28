package racemanager2000.Game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping(value = "/createseason/")
    public Season postStart(@RequestBody Map<String, Integer> body) throws Exception {
        seasonrunner.startSeason(body.get("seasonname"));
        return seasonRepository.getSeasonBySeasonname(body.get("seasonname"));
    }

    @PostMapping(value = "/finishseason")
    public List<Seasonresult> postFinish(@RequestBody int body) throws Exception {
        Season season = seasonRepository.getSeasonBySeasonname(body);
        seasonrunner.finishSeason(season);

        return seasonresultsRepository.findAllBySeasonIdOrderBySeasonPointsDesc(season.getId());
    }

    @GetMapping(value = "/seasons")
    public List<Season> getSeason() throws Exception {
        return seasonRepository.findAllByOrderBySeasonnameDesc();
    }
}
