package com.mobility.authservice.strategy.Impl;

import com.mobility.authservice.dto.UserResponseDTO;
import com.mobility.authservice.enums.RoleType;
import com.mobility.authservice.strategy.AuthenticationStrategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class RiderAuthenticationStrategy implements AuthenticationStrategy {
    private final PasswordEncoder passwordEncoder;

    public RiderAuthenticationStrategy(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean authenticate(UserResponseDTO user, String rawPassword) {
        return user.getRole() == RoleType.RIDER && passwordEncoder.matches(rawPassword, user.getPassword());
    }
}
