package com.mobility.authservice.exceptions.exception_handlers;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobility.authservice.dto.ApiResponse;

import feign.FeignException;

public abstract class AbstractFeignExceptionHandler implements IFeignExceptionHandler {
    protected final ObjectMapper objectMapper;
    protected final IStatusCodeMapper statusCodeMapper;
    protected final IErrorMessageExtractor errorMessageExtractor;

    protected AbstractFeignExceptionHandler(
            ObjectMapper objectMapper,
            IStatusCodeMapper statusCodeMapper,
            IErrorMessageExtractor errorMessageExtractor) {
        this.objectMapper = objectMapper;
        this.statusCodeMapper = statusCodeMapper;
        this.errorMessageExtractor = errorMessageExtractor;
    }

    @Override
    public ResponseEntity<ApiResponse<String>> handleFeignException(FeignException ex) {
        try {
            String errorMessage = errorMessageExtractor.extractErrorMessage(ex, objectMapper);
            HttpStatus status = statusCodeMapper.mapStatus(ex.status(), HttpStatus.INTERNAL_SERVER_ERROR);

            return handleException(ex, errorMessage, status);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>("error", "An unexpected error occurred", null, Instant.now()));
        }
    }

    protected abstract ResponseEntity<ApiResponse<String>> handleException(
            FeignException ex,
            String errorMessage,
            HttpStatus status);
}
