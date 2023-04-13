package com.medicensoya.valetapp.repositories;

import com.medicensoya.valetapp.domain.Technician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Repository
@Transactional(readOnly = true)
public interface TechnicianRepository extends JpaRepository<Technician, Long> {
    Technician getTechnicianById(Long id);

}
