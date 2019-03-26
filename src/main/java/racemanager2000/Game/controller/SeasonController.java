package racemanager2000.Game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import racemanager2000.Game.model.Season;
import racemanager2000.Game.model.Seasonresult;
import racemanager2000.Game.repository.SeasonRepository;
import racemanager2000.Game.repository.SeasonresultsRepository;
import racemanager2000.Game.service.Seasonrunner;

import java.util.List;

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

    @PostMapping(value = "/startseason/")
    public Season postStart(@RequestBody int body) throws Exception {
        seasonrunner.startSeason(body);
        return seasonRepository.getSeasonBySeasonname(body);
    }

    @PostMapping(value = "/finishseason")
    public List<Seasonresult> postFinish(@RequestBody int body) throws Exception {
        Season season = seasonRepository.getSeasonBySeasonname(body);
        seasonrunner.finishSeason(season);

        return seasonresultsRepository.findAllBySeasonIdOrderBySeasonPointsDesc(season.getId());
    }

    @GetMapping(value = "/season")
    public List<Season> getSeason() throws Exception {
        return seasonRepository.findAllByOrderBySeasonnameDesc();
    }
}
