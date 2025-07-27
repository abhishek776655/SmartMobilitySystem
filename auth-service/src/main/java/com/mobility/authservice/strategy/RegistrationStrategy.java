package com.mobility.authservice.strategy;

import com.mobility.authservice.dto.RegisterUserRequest;
import com.mobility.authservice.dto.UserResponseDTO;

public interface RegistrationStrategy<T extends RegisterUserRequest> {
    UserResponseDTO register(T request);
}