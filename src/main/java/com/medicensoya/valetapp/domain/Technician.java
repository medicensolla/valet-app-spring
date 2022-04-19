package com.medicensoya.valetapp.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Technician extends User {

    @Id
    @SequenceGenerator(
            name = "technician_sequence",
            sequenceName = "technician_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "technician_sequence"
    )
    private Long id;

    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private final AppUserRole appUserRole = AppUserRole.TECH;

    @OneToMany(mappedBy = "technician")
    private Set<Car> requestedCars;

    public Technician(String username, String password, String firstName, String lastName,
                      Set<Car> requestedCars) {
        super(username, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.requestedCars = requestedCars;
    }

    public Technician(String username, String password, String firstName, String lastName) {
        super(username, password);
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
