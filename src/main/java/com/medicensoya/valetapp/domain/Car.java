package com.medicensoya.valetapp.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue
    private Long id;

    private String tagNumber;

    @ManyToOne
    @JoinColumn(name = "valet_id", nullable = false)
    private Valet valet;

    @ManyToOne
    @JoinColumn(name = "technician_id", nullable = false)
    private Technician technician;

}
