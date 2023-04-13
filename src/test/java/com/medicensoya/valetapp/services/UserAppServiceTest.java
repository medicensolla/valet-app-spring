package com.medicensoya.valetapp.services;

import com.medicensoya.valetapp.domain.UserApp;
import com.medicensoya.valetapp.repositories.TechnicianRepository;
import com.medicensoya.valetapp.repositories.UserAppRepostory;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static com.medicensoya.valetapp.services.UserAppService.USER_NOT_FOUND_MSG;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserAppServiceTest {

    @InjectMocks
    private UserAppService userAppService;

    @Mock
    private UserAppRepostory userAppRepostory;

    @Mock
    private  BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private TechnicianRepository technicianRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userAppService = new UserAppService(userAppRepostory,technicianRepository, bCryptPasswordEncoder);

    }

    @Test
    public void shouldThrowExceptionWhenUserNotFound() {
        String username = "jane.doe";
        when(userAppRepostory.findUserAppByUsername(anyString())).thenReturn(Optional.empty());

        UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class,
                () -> userAppService.loadUserByUsername(username));

        assertEquals(String.format(USER_NOT_FOUND_MSG, username), exception.getMessage());
    }
}
