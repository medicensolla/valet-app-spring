package com.medicensoya.valetapp.services;

import com.medicensoya.valetapp.domain.Car;
import com.medicensoya.valetapp.domain.Technician;
import com.medicensoya.valetapp.dto.CarDto;
import com.medicensoya.valetapp.dto.TechnicianDto;
import com.medicensoya.valetapp.repositories.TechnicianRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@AllArgsConstructor
public class TechnicianService {

    private final TechnicianRepository technicianRepository;

    /**
     * Creation of a Technician
     *
     * @param technicianDto body of the request to save a technician in the DB
     * @return a Dto with the technician already saved
     */
    public TechnicianDto createTechnician(TechnicianDto technicianDto) {
        Technician newTechnician = this.converterFromDtoToObject(technicianDto);
        Technician technicianResponse = this.technicianRepository.save(newTechnician);
        return this.converterFromObjToDto(technicianResponse);
    }

    public TechnicianDto requestCars(Long idTechnician,
                                     Set<Car> requestedCars) {
        Technician technicianUpdate = this.technicianRepository.getById(idTechnician);
        technicianUpdate.setRequestedCars(requestedCars);
        Technician technicianResponse = this.technicianRepository.save(technicianUpdate);
        return this.converterFromObjToDto(technicianResponse);


    }


    private Technician converterFromDtoToObject(TechnicianDto technicianDto) {

        Technician technician = new Technician();

        BeanUtils.copyProperties(technicianDto, technician);

        return technician;
    }

    private TechnicianDto converterFromObjToDto(Technician technician) {

        TechnicianDto technicianDto = new TechnicianDto();

        if (this.technicianValidations(technician)) {

            BeanUtils.copyProperties(technician, technicianDto);

        }

        return technicianDto;
    }

    private Boolean technicianValidations(Technician technician) {

        boolean isValid = false;

        if (Objects.nonNull(technician)) {

            if (!StringUtils.hasText(technician.getFirstName())) {

                throw new IllegalStateException("First Name is Mandatory");

            } else if (!StringUtils.hasText(technician.getLastName())) {
                throw new IllegalStateException("Last Name is Mandatory");

            } else if (!StringUtils.hasText(technician.getUsername())) {
                throw new IllegalStateException("UserName is Mandatory");
            } else {
                isValid = true;
            }

        } else {
            throw new IllegalStateException("Technician can't be empty");
        }

        return isValid;
    }


}
