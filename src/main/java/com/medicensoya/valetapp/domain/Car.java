package com.medicensoya.valetapp.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Car {

    @Id
    @SequenceGenerator(
            name = "car_sequence",
            sequenceName = "car_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "car_sequence"
    )
    @Column(name = "car_id")
    private Long id;

    private String tagNumber;

    @ManyToOne
    @JoinColumn(name = "valet_id")
    private Valet valet;

    @ManyToOne
    @JoinColumn(name = "technician_id")
    private Technician technician;

    public Car(String tagNumber, Technician technician) {
        this.tagNumber = tagNumber;
        this.technician = technician;
    }
}
