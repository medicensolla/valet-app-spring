package com.medicensoya.valetapp.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public abstract class UserApp {

    private String username;
    private String password;


    public UserApp(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
