package com.mobility.authservice.exceptions.exception_handlers;

import org.springframework.http.HttpStatus;

public interface IStatusCodeMapper {
    HttpStatus mapStatus(int statusCode, HttpStatus defaultStatus);
}
