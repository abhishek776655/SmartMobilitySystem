package com.mobility.authservice.dto;

import jakarta.validation.constraints.NotBlank;

public class DriverRegisterRequest extends RegisterUserRequest {
    @NotBlank
    private String licenseNumber;
}
