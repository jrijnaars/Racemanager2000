package racemanager2000.Game.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racemanager2000.Game.model.Raceresult;
import racemanager2000.Game.repository.RaceresultsRepository;
import racemanager2000.Game.repository.SeasonRepository;
import racemanager2000.Game.service.Racerunner;

import java.util.List;

@CrossOrigin
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

    @PostMapping(value="/startrace")
    public List<Raceresult> post(@RequestBody String racename, Integer seasonname) throws Exception {
        racerunner.runRace(racename,seasonname);
        return raceresultsRepository.findAllBySeasonId(seasonRepository.getSeasonBySeasonname(seasonname).getId());
    }

}
