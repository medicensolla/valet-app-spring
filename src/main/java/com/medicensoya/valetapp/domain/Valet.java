package com.medicensoya.valetapp.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Valet {

    @Id
    @SequenceGenerator(
            name = "valet_sequence",
            sequenceName = "valet_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "valet_sequence"
    )
    private Long id;

    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "valet", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Car> cars;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_app_id", referencedColumnName = "id")
    private UserApp userApp;

    public Valet(String username, String password, String firstName, String lastName, Set<Car> cars) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cars = cars;
    }
}
