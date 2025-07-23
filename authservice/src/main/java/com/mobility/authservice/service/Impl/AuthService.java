package com.mobility.authservice.service.Impl;

import com.mobility.authservice.dto.AuthResponse;
import com.mobility.authservice.dto.RegisterUserRequest;
import com.mobility.authservice.model.User;
import com.mobility.authservice.repository.UserRepository;
import com.mobility.authservice.service.IAuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.mobility.authservice.model.Role;

import java.util.Optional;

public class AuthService implements IAuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public AuthResponse register(RegisterUserRequest request) {

        Optional<User> existing = userRepository.findByEmail((request.))
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhoneNumber((request.getPhoneNumber()));
        user.setPassword((passwordEncoder.encode((request.getPassword()))));
        try {
            Role role = Role.valueOf(request.getRole().toUpperCase());
            user.setRole(role);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role: " + request.getRole());
        }
        userRepository.save(user);
        return new AuthResponse(token, "User Registered Successfully",user.getId(), user.getRole());

    }
}
