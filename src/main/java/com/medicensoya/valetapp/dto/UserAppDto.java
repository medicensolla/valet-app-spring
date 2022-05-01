package com.medicensoya.valetapp.dto;

import com.medicensoya.valetapp.domain.AppUserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserAppDto {

    private final String firstName;
    private final String lastName;
    private AppUserRole appUserRole;
    private final String username;
    private final String password;
}
