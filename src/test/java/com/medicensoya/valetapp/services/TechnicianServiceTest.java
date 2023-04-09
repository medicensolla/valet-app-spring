package com.medicensoya.valetapp.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.medicensoya.valetapp.domain.Car;
import com.medicensoya.valetapp.domain.Technician;
import com.medicensoya.valetapp.dto.TechnicianDto;
import com.medicensoya.valetapp.exception.ApiRequestException;
import com.medicensoya.valetapp.repositories.CarRepository;
import com.medicensoya.valetapp.repositories.TechnicianRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class TechnicianServiceTest {

    @Mock
    private TechnicianRepository technicianRepository;

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private TechnicianService technicianService;


    @BeforeEach
    public void setUp() {
        technicianService = new TechnicianService(technicianRepository,carRepository);
    }

    @Test
    public void testTechnicianValidations() {
        Technician technician = new Technician("John", "Doe");

        assertTrue(technicianService.technicianValidations(technician));

        verify(technicianRepository, never()).save(Mockito.any(Technician.class));
    }

    @Test
    public void testTechnicianValidationsEmptyTechnician() {
        assertThrows(ApiRequestException.class, () -> technicianService.technicianValidations(null));
    }

    @Test
    public void testTechnicianValidationsEmptyFirstName() {
        Technician technician = new Technician("", "Doe");

        assertThrows(ApiRequestException.class, () -> technicianService.technicianValidations(technician));
    }

    @Test
    public void testTechnicianValidationsEmptyLastName() {
        Technician technician = new Technician("John", "");

        assertThrows(ApiRequestException.class, () -> technicianService.technicianValidations(technician));
    }

    @Test
    void isCarAlreadyRequested_should_throw_exception_if_tagNumber_is_longer_than_4_digits() {
        // Arrange
        Set<Car> requestedCars = new HashSet<>();
        Car car = new Car("12345", null);
        requestedCars.add(car);

        // Act & Assert
        assertThrows(ApiRequestException.class, () -> technicianService.isCarAlreadyRequested(requestedCars));
    }

    @Test
    void isCarAlreadyRequested_should_throw_exception_if_car_already_exists() {
        // Arrange
        Set<Car> requestedCars = new HashSet<>();
        Car car = new Car("1234", null);
        requestedCars.add(car);
        when(carRepository.existsByTagNumber(anyString())).thenReturn(true);

        // Act & Assert
        assertThrows(ApiRequestException.class, () -> technicianService.isCarAlreadyRequested(requestedCars));
    }

    @Test
    void isCarAlreadyRequested_should_not_throw_exception_if_no_requested_cars() {
        // Arrange
        Set<Car> requestedCars = null;

        // Act & Assert
        technicianService.isCarAlreadyRequested(requestedCars);
    }

    @Test
    void isCarAlreadyRequested_should_not_throw_exception_if_no_duplicate_cars() {
        // Arrange
        Set<Car> requestedCars = new HashSet<>();
        Car car = new Car("1234", null);
        requestedCars.add(car);
        when(carRepository.existsByTagNumber(anyString())).thenReturn(false);

        // Act & Assert
        technicianService.isCarAlreadyRequested(requestedCars);
    }

}

