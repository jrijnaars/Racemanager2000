package racemanager2000.Game.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racemanager2000.Game.model.Raceresult;
import racemanager2000.Game.repository.RaceresultsRepository;
import racemanager2000.Game.repository.SeasonRepository;
import racemanager2000.Game.service.Racerunner;

import java.util.List;
import java.util.Map;

@RestController
public class RaceController {

    private Racerunner racerunner;

    private SeasonRepository seasonRepository;

    private RaceresultsRepository raceresultsRepository;
    @Autowired
    public RaceController(Racerunner racerunner, SeasonRepository seasonRepository, RaceresultsRepository raceresultsRepository) {
        this.racerunner = racerunner;
        this.seasonRepository = seasonRepository;
        this.raceresultsRepository = raceresultsRepository;
    }

    @PostMapping("/startrace")
    public List<Raceresult> post(@RequestBody Map<String, String> body) throws Exception {
        racerunner.runRace(body.get("racename"), body.get("seasonname"));
        String seasonname = body.get("seasonname");
        return raceresultsRepository.findAllBySeasonId(seasonRepository.getSeasonBySeasonname(seasonname).getId());
    }

}
