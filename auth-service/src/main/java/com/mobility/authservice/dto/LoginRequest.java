package com.mobility.authservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    @Email
    private String email;

    @NotBlank
    private String password;

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    // Getters and setters
}