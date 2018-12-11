package racemanager2000.Game.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import racemanager2000.Game.model.Race;

@Transactional
@RepositoryRestResource(collectionResourceRel = "race", path = "races")
public interface RaceRepository
        extends PagingAndSortingRepository<Race, Long> {

    @Transactional
    Race getByRacename(String name);
}
