package com.medicensoya.valetapp.repositories;

import com.medicensoya.valetapp.domain.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UserAppRepostory extends JpaRepository<UserApp, Long> {

    Optional<UserApp> findUserAppByUsername(String username);
}
