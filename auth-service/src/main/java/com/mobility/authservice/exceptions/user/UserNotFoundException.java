package com.mobility.authservice.exceptions.user;

import com.mobility.authservice.exceptions.CustomExceptionService;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends CustomExceptionService {
    public UserNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}