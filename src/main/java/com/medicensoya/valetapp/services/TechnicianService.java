package com.medicensoya.valetapp.services;

import com.medicensoya.valetapp.domain.Car;
import com.medicensoya.valetapp.domain.Technician;
import com.medicensoya.valetapp.dto.TechnicianDto;
import com.medicensoya.valetapp.exception.ApiRequestException;
import com.medicensoya.valetapp.repositories.TechnicianRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
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
        if (this.technicianValidations(newTechnician)) {
            Technician technicianResponse = this.technicianRepository.save(newTechnician);
            technicianDto = this.converterFromObjToDto(technicianResponse);
        }
        return technicianDto;
    }

    /**
     * Service for the technicians that ables them to request cars/
     *
     * @param idTechnician  Technician requesting this cars
     * @param requestedCars The cars that are being requested
     * @return String with message
     */
    public String requestCars(Long idTechnician,
                              Set<Car> requestedCars) {
        Technician technicianUpdate = this.technicianRepository.getTechnicianById(idTechnician);
        if (Objects.nonNull(technicianUpdate)) {
            requestedCars.forEach(car -> car.setTechnician(technicianUpdate));
            technicianUpdate.getRequestedCars().addAll(requestedCars);
            this.technicianRepository.save(technicianUpdate);
        } else {
            throw new ApiRequestException("This Technician doesn't have access");
        }
        //TODO Change response
        return "Success";
    }


    private Technician converterFromDtoToObject(TechnicianDto technicianDto) {

        Technician technician = new Technician();

        BeanUtils.copyProperties(technicianDto, technician);

        return technician;
    }

    private TechnicianDto converterFromObjToDto(Technician technician) {

        TechnicianDto technicianDto = new TechnicianDto();
        BeanUtils.copyProperties(technician, technicianDto);
        return technicianDto;
    }

    private Boolean technicianValidations(Technician technician) {

        boolean isValid = false;

        if (Objects.nonNull(technician)) {

            if (!StringUtils.hasText(technician.getFirstName())) {

                throw new ApiRequestException("First Name is Mandatory");

            } else if (!StringUtils.hasText(technician.getLastName())) {
                throw new ApiRequestException("Last Name is Mandatory");

            } else {
                isValid = true;
            }

        } else {
            throw new ApiRequestException("Technician can't be empty");
        }

        return isValid;
    }


}
