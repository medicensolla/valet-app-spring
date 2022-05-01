package com.medicensoya.valetapp.services;

import com.medicensoya.valetapp.domain.AppUserRole;
import com.medicensoya.valetapp.domain.Technician;
import com.medicensoya.valetapp.domain.UserApp;
import com.medicensoya.valetapp.dto.UserAppDto;
import com.medicensoya.valetapp.exception.ApiRequestException;
import com.medicensoya.valetapp.repositories.TechnicianRepository;
import com.medicensoya.valetapp.repositories.UserAppRepostory;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Service
@AllArgsConstructor
public class UserAppService implements UserDetailsService {


    private final static String USER_NOT_FOUND_MSG = "user with username %s not found";
    private final UserAppRepostory appUserRepository;
    private final TechnicianRepository technicianRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        return appUserRepository.findUserAppByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format(USER_NOT_FOUND_MSG, username)));
    }

    public UserAppDto signUpUser(UserAppDto userAppDto) {

        this.userValidations(userAppDto);

        UserApp newUser = this.converterFromDtoToObject(userAppDto);

        boolean userExists = appUserRepository.findUserAppByUsername(userAppDto.getUsername()).isPresent();

        if (userExists) {
            throw new ApiRequestException("Username Already Taken");
        }


        String encodedPassword = bCryptPasswordEncoder.encode(userAppDto.getPassword());

        newUser.setPassword(encodedPassword);

        if (userAppDto.getAppUserRole().equals(AppUserRole.TECH)) {
            this.createTechnician(userAppDto, newUser);
        }

        appUserRepository.save(newUser);


        return userAppDto;

    }

    private void createTechnician(UserAppDto userAppDto, UserApp userApp) {

        Technician technician = new Technician();
        technician.setFirstName(userAppDto.getFirstName());
        technician.setLastName(userAppDto.getLastName());
        technician.setUserApp(userApp);
        this.technicianRepository.save(technician);

    }


    private void userValidations(UserAppDto userApp) {


        if (Objects.nonNull(userApp)) {

            if (!StringUtils.hasText(userApp.getUsername())) {

                throw new ApiRequestException("Username is Mandatory");

            } else if (!StringUtils.hasText(userApp.getPassword())) {
                throw new ApiRequestException("Password is Mandatory");

            } else if (!StringUtils.hasText(userApp.getFirstName())) {

                throw new ApiRequestException("First Name is Mandatory");

            } else if (!StringUtils.hasText(userApp.getLastName())) {
                throw new ApiRequestException("Last Name is Mandatory");

            }

        } else {
            throw new ApiRequestException("User can't be empty");
        }


    }


    private UserApp converterFromDtoToObject(UserAppDto userAppDto) {

        UserApp userApp = new UserApp();

        BeanUtils.copyProperties(userAppDto, userApp);

        return userApp;
    }

}
