package racemanager2000.Game.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import racemanager2000.Game.model.Season;

import java.util.List;

@Transactional
@RepositoryRestResource(collectionResourceRel = "season", path = "seasons")
public interface SeasonRepository
        extends PagingAndSortingRepository<Season, Long> {

    @Transactional
    Season getSeasonBySeasonname(int seasonname);

    List<Season> findAllByOrderBySeasonnameDesc();

}
