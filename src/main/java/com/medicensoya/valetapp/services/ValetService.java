package com.medicensoya.valetapp.services;

import com.medicensoya.valetapp.repositories.CarRepository;
import com.medicensoya.valetapp.repositories.ValetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ValetService {

    private final ValetRepository valetRepository;

    private final CarService carService;





    public void getCarsToTechnicians(List<String> tagNumbersForCarsToRetrieve) {




    }


}
