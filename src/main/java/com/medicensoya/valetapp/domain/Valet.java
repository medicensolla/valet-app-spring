package com.medicensoya.valetapp.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Valet extends User {

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
    @Column(name = "valet_id")
    private Long id;

    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole;

    @OneToMany(mappedBy = "valet")
    private Set<Car> cars;

    public Valet(String username, String password, String firstName, String lastName, AppUserRole appUserRole, Set<Car> cars) {
        super(username, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.appUserRole = appUserRole;
        this.cars = cars;
    }
}
