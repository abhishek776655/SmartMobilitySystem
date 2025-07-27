package com.mobility.authservice.strategy;

import com.mobility.authservice.dto.UserResponseDTO;

public interface AuthenticationStrategy {
    boolean authenticate(UserResponseDTO user, String rawPassword);
}
