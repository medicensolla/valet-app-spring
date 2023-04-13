package com.medicensoya.valetapp.controllers;

import com.medicensoya.valetapp.domain.Car;
import com.medicensoya.valetapp.dto.CarDto;
import com.medicensoya.valetapp.repositories.CarRepository;
import com.medicensoya.valetapp.services.CarService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/car")
@AllArgsConstructor
public class CarController {

    private final CarService carService;

    private final CarRepository carRepository;

    @GetMapping("/cars")
    public ResponseEntity<List<CarDto>> getAllCars() {
        List<CarDto> carDtos = carService.retrieveAllCars();
        return ResponseEntity.ok().body(carDtos);

    }


}
