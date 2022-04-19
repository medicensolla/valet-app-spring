package com.medicensoya.valetapp.controllers;

import com.medicensoya.valetapp.domain.Car;
import com.medicensoya.valetapp.dto.TechnicianDto;
import com.medicensoya.valetapp.services.TechnicianService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(path = "api/v1/technician")
@AllArgsConstructor
public class TechnicianController {

    private final TechnicianService technicianService;


    @PostMapping("/create-technician")
    public ResponseEntity<TechnicianDto> createTechnician(@RequestBody TechnicianDto technicianDto) {

        TechnicianDto technicianResponseDto = this.technicianService.createTechnician(technicianDto);

        return new ResponseEntity<>(technicianResponseDto, HttpStatus.OK);

    }

    @PutMapping("/request-car")
    public ResponseEntity<TechnicianDto> requestACar(@RequestParam Long technicianId,
                                                     @RequestBody Set<Car> requestedCars) {

        TechnicianDto technicianResponseDto = this.technicianService.requestCars(technicianId, requestedCars);

        return new ResponseEntity<>(technicianResponseDto, HttpStatus.OK);

    }


}
