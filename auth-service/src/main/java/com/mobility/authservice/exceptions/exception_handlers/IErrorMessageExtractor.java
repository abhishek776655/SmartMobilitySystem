package com.mobility.authservice.exceptions.exception_handlers;

import com.fasterxml.jackson.databind.ObjectMapper;

import feign.FeignException;

public interface IErrorMessageExtractor {
    String extractErrorMessage(FeignException ex, ObjectMapper objectMapper);

}
