package com.medicensoya.valetapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public abstract class User {


    private String username;

    private String password;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
