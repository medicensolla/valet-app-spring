package com.medicensoya.valetapp.controllers;

import com.medicensoya.valetapp.dto.UserAppDto;
import com.medicensoya.valetapp.services.UserAppService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/user")
@AllArgsConstructor
public class UserAppController {

    private final UserAppService userAppService;


    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody UserAppDto request) {

        UserAppDto userAppDto = this.userAppService.signUpUser(request);

        return new ResponseEntity<>(userAppDto, HttpStatus.OK);
    }

}
