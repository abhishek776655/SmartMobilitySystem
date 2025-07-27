package com.mobility.authservice.exceptions.exception_handlers.Impl;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobility.authservice.dto.ApiResponse;
import com.mobility.authservice.exceptions.auth.BadRequestException;
import com.mobility.authservice.exceptions.auth.ForbiddenException;
import com.mobility.authservice.exceptions.auth.UnauthorizedException;
import com.mobility.authservice.exceptions.exception_handlers.AbstractFeignExceptionHandler;
import com.mobility.authservice.exceptions.exception_handlers.IErrorMessageExtractor;
import com.mobility.authservice.exceptions.exception_handlers.IStatusCodeMapper;
import com.mobility.authservice.exceptions.user.UserAlreadyExistsException;
import com.mobility.authservice.exceptions.user.UserNotFoundException;

import feign.FeignException;

@Component
public class DefaultFeignExceptionHandler extends AbstractFeignExceptionHandler {

    public DefaultFeignExceptionHandler(
            ObjectMapper objectMapper,
            IStatusCodeMapper statusCodeMapper,
            IErrorMessageExtractor errorMessageExtractor) {
        super(objectMapper, statusCodeMapper, errorMessageExtractor);
    }

    @Override
    protected ResponseEntity<ApiResponse<String>> handleException(
            FeignException ex,
            String errorMessage,
            HttpStatus status) {

        switch (status) {
            case CONFLICT:
                if (errorMessage.toLowerCase().contains("user") &&
                        errorMessage.toLowerCase().contains("exist")) {
                    throw new UserAlreadyExistsException(extractEmailFromMessage(errorMessage));
                }
                break;
            case NOT_FOUND:
                if (errorMessage.toLowerCase().contains("user")) {
                    throw new UserNotFoundException("User not found");
                }
                break;
            case UNAUTHORIZED:
                throw new UnauthorizedException("Authentication failed");
            case FORBIDDEN:
                throw new ForbiddenException("Access denied");
            case BAD_REQUEST:
                throw new BadRequestException("Invalid request: " + errorMessage);
            default:
                break;
        }

        return ResponseEntity.status(status)
                .body(new ApiResponse<>("error", errorMessage, null, Instant.now()));
    }

    private String extractEmailFromMessage(String message) {
        if (message != null && message.matches("(?i).*email.*already exists.*")) {
            return message.replaceAll("(?i).*email\\s+([^\\s]+).*", "$1");
        }
        return "unknown";
    }
}
