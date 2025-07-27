package com.mobility.authservice.exceptions.exception_handlers.Impl;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobility.authservice.dto.ApiResponse;
import com.mobility.authservice.exceptions.exception_handlers.IErrorMessageExtractor;

import feign.FeignException;

@Component
public class DefaultErrorMessageExtractor implements IErrorMessageExtractor {
    @Override
    public String extractErrorMessage(FeignException ex, ObjectMapper objectMapper) {
        if (ex.responseBody().isPresent()) {
            try {
                String responseBody = ex.contentUTF8();
                ApiResponse<?> errorResponse = objectMapper.readValue(responseBody, ApiResponse.class);
                return errorResponse.getMessage() != null ? errorResponse.getMessage()
                        : "Error communicating with service";
            } catch (Exception e) {
            }
        }
        return "Error communicating with service";
    }
}
