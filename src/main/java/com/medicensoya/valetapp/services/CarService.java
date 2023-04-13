package com.medicensoya.valetapp.services;

import com.medicensoya.valetapp.domain.Car;
import com.medicensoya.valetapp.domain.Technician;
import com.medicensoya.valetapp.dto.CarDto;
import com.medicensoya.valetapp.dto.TechnicianDto;
import com.medicensoya.valetapp.repositories.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarService {

    private final CarRepository carRepository;


    public List<CarDto> retrieveAllCars() {
        List<Car> cars = carRepository.findAll();
        return cars.stream().map(this::converterFromObjToDto).collect(Collectors.toList());
    }

    public List<Car> retrieveCarsByTagNumbers(Set<String> tagNumbers) {
        return carRepository.findCarsByTagNumberIn(tagNumbers);
    }


    private CarDto converterFromObjToDto(Car car) {

        CarDto carDto = new CarDto();
        BeanUtils.copyProperties(car, carDto);
        return carDto;
    }


}
