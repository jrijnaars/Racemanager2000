package racemanager2000.Game.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import racemanager2000.Game.model.Raceresult;

import java.util.List;

@Transactional
@RepositoryRestResource(collectionResourceRel = "raceresult", path = "raceresults")
public interface RaceresultsRepository
        extends PagingAndSortingRepository<Raceresult, Long> {

    List<Raceresult> findAllBySeasonId(Long seasinId);
}
