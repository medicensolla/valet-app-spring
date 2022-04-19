package com.medicensoya.valetapp.dto;

import com.medicensoya.valetapp.domain.Technician;
import com.medicensoya.valetapp.domain.Valet;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CarDto {

    private Long id;

    private String tagNumber;

    private Valet valet;

    private Technician technician;
}
