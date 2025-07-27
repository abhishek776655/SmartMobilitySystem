package com.mobility.authservice.exceptions.user;

import org.springframework.http.HttpStatus;

import com.mobility.authservice.exceptions.CustomExceptionService;

public class UserAlreadyExistsException extends CustomExceptionService {
    public UserAlreadyExistsException(String email) {
        super("User with email " + email + " already exists", HttpStatus.CONFLICT);
    }
}
