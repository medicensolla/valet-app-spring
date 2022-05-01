package com.medicensoya.valetapp.services;

import com.medicensoya.valetapp.domain.UserApp;
import com.medicensoya.valetapp.exception.ApiRequestException;
import com.medicensoya.valetapp.repositories.UserAppRepostory;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserAppService implements UserDetailsService {


    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
    private final UserAppRepostory appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        return appUserRepository.findUserAppByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format(USER_NOT_FOUND_MSG, username)));
    }

    public String signUpUser(UserApp appUser) {
        boolean userExists = appUserRepository.findUserAppByUsername(appUser.getUsername()).isPresent();

        if (userExists) {
            throw new ApiRequestException("Username Already Taken");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());

        appUser.setPassword(encodedPassword);

        appUserRepository.save(appUser);

        String token = UUID.randomUUID().toString();

        return token;

    }
}
