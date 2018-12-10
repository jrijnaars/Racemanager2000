package racemanager2000.Game.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import racemanager2000.Game.model.Car;

@Transactional
@RepositoryRestResource(collectionResourceRel = "car", path = "cars")
public interface CarRepository
        extends PagingAndSortingRepository<Car, Long> {

    @Transactional
    Car getByName(String name);
}
