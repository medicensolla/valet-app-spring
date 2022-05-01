package com.medicensoya.valetapp.repositories;

import com.medicensoya.valetapp.domain.Car;
import com.medicensoya.valetapp.domain.Technician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface CarRepository extends JpaRepository<Car, Long> {

    Boolean existsByTagNumber(String tagNumber);

}
