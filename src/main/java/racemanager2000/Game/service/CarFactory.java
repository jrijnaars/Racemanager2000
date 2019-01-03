package racemanager2000.Game.service;


import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racemanager2000.Game.model.Car;
import racemanager2000.Game.repository.CarRepository;

@Service
public class CarFactory {

    private CarRepository carRepository;

    @Autowired
    public CarFactory(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void createOwnTeam(String carname) throws Exception {
        Car existingCar = carRepository.getByName(carname);
        if (existingCar == null){
            Integer chassis = RandomUtils.nextInt(80, 98);
            Integer engine = RandomUtils.nextInt(80, 98);
            Car ownTeam = new Car(carname, chassis, engine);
            ownTeam.setCarAbillityOverall();
            carRepository.save(ownTeam);
        } else {
            throw new Exception("Car is already created");
        }
    }
}
