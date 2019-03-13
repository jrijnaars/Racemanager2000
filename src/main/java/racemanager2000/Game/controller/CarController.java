package racemanager2000.Game.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import racemanager2000.Game.model.Car;
import racemanager2000.Game.repository.CarRepository;
import racemanager2000.Game.service.CarFactory;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class CarController {

    final private CarFactory carFactory;

    final private CarRepository carRepository;

    @Autowired
    public CarController(CarFactory carFactory, CarRepository carRepository) {
        this.carFactory = carFactory;
        this.carRepository = carRepository;
    }

    @PostMapping("/cars")
    public Car postCreateCar(@RequestBody Map<String, String> body) throws Exception {
        String carname = body.get("carname");
        carFactory.createOwnTeam(carname);
        return carRepository.getByName(carname);
    }

    @GetMapping("/cars")
    public List<Car> getCars() throws Exception {
        return carRepository.findAllByOrderByCarAbillityOverallDesc();
    }
}