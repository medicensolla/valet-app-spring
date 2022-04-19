package com.medicensoya.valetapp.dto;

import com.medicensoya.valetapp.domain.Car;
import com.medicensoya.valetapp.domain.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class ValetDto extends User {

    private String firstName;
    private String lastName;
    private Set<Car> cars;

    public ValetDto(String username, String password, String firstName, String lastName) {
        super(username, password);
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
