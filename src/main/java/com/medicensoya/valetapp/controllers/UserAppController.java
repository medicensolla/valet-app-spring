package com.medicensoya.valetapp.controllers;

import com.medicensoya.valetapp.dto.UserAppDto;
import com.medicensoya.valetapp.services.UserAppService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/user")
@AllArgsConstructor
public class UserAppController {

    private final UserAppService userAppService;


    /**
     * Creates a new user with the given data and
     * returns a ResponseEntity object with the created
     * user data in the response body and HTTP status code 201 (Created).
     *
     * @param request a UserAppDto object containing the data for the new user to be created
     * @return a ResponseEntity object with the created user data in the response body and HTTP status code 201 (Created)
     */
    @PostMapping("/create")
    public ResponseEntity<UserAppDto> create(@RequestBody UserAppDto request) {

        UserAppDto userAppDto = this.userAppService.signUpUser(request);

        return new ResponseEntity<>(userAppDto, HttpStatus.CREATED);
    }



}
