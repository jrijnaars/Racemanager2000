package racemanager2000.Game.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racemanager2000.Game.model.Car;
import racemanager2000.Game.repository.CarRepository;
import racemanager2000.Game.service.CarFactory;

import java.util.Map;

@RestController
public class CarController {

    final private CarFactory carFactory;

    final private CarRepository carRepository;

    @Autowired
    public CarController(CarFactory carFactory, CarRepository carRepository) {
        this.carFactory = carFactory;
        this.carRepository = carRepository;
    }

    @PostMapping("/createCar")
    public Car postCreateCar(@RequestBody Map<String, String> body) throws Exception {
        carFactory.createOwnTeam(body.get("carname"));
        return carRepository.getByName("carname");
    }

}
