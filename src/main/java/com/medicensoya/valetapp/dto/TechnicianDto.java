package com.medicensoya.valetapp.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.medicensoya.valetapp.domain.Car;
import com.medicensoya.valetapp.domain.UserApp;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class TechnicianDto extends UserApp {

    private Long id;
    private String firstName;
    private String lastName;
    @JsonIgnore
    private Set<Car> requestedCars;
}
