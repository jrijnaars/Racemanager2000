package racemanager2000.Game.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import racemanager2000.Game.model.Seasonresult;

import java.util.List;

@Transactional
@RepositoryRestResource(collectionResourceRel = "seasonresult", path = "seasonresults")
public interface SeasonresultsRepository
        extends PagingAndSortingRepository<Seasonresult, Long> {

    Seasonresult getSeasonresultsByCarIdAndSeasonId(Long carId, Long seasonId);

    List<Seasonresult> findAllBySeasonIdOrderBySeasonPointsDesc(Long seasonId);

}
