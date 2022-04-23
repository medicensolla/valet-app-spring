package com.medicensoya.valetapp.dto;

import com.medicensoya.valetapp.domain.Car;
import com.medicensoya.valetapp.domain.UserApp;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class ValetDto extends UserApp {

    private String firstName;
    private String lastName;
    private Set<Car> cars;

    public ValetDto(String username, String password, String firstName, String lastName) {
        super(username, password);
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
