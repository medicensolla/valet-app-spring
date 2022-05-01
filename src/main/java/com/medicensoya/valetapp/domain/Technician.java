package com.medicensoya.valetapp.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Technician {

    @Id
    @SequenceGenerator(
            name = "technician_sequence",
            sequenceName = "technician_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "technician_sequence"
    )
    private Long id;

    private String firstName;
    private String lastName;


    @OneToMany(mappedBy = "technician", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Car> requestedCars = new HashSet<>();

    @OneToOne(mappedBy = "technician")
    private UserApp userApp;

    public Technician(String firstName, String lastName,
                      Set<Car> requestedCars) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.requestedCars = requestedCars;
    }

    public Technician(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
