package com.medicensoya.valetapp.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.medicensoya.valetapp.domain.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TechnicianDto {

    private String firstName;
    private String lastName;
    @JsonIgnore
    private Set<Car> requestedCars;

    public TechnicianDto(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
