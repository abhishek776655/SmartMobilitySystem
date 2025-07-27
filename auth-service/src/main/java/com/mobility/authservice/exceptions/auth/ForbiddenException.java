package com.mobility.authservice.exceptions.auth;

import org.springframework.http.HttpStatus;

import com.mobility.authservice.exceptions.CustomExceptionService;

public class ForbiddenException extends CustomExceptionService {
    public ForbiddenException(String message) {
        super(message, HttpStatus.FORBIDDEN);
    }
}
