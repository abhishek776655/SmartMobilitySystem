package com.mobility.authservice.controller;

import com.mobility.authservice.client.UserClient;
import com.mobility.authservice.dto.*;

import com.mobility.authservice.enums.RoleType;
import com.mobility.authservice.service.IAuthService;
import com.mobility.authservice.service.IRegisterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mobility.authservice.dto.ApiResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final IAuthService authService;
    private final IRegisterService registerService;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserClient userClient;

    @Autowired
    public AuthController(IAuthService authService, IRegisterService registerService) {
        this.authService = authService;
        this.registerService = registerService;
    }

sntroller
@RequestMapping("/api/auth")
public class AuthController {

    private final IAuthService authService;
    private final IRegisterService registerService;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserClient userClient;

    @Autowired
    public AuthController(IAuthService authService, IRegisterService registerService) {
        this.authService = authService;
        this.registerService = registerService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponseDTO>> register(@Valid @RequestBody RegisterUserRequest request) {
        if (request.getRole() == null || !(request.getRole().equals(RoleType.RIDER.toString())
                || request.getRole().equals(RoleType.DRIVER.toString()))) {
            System.out.println(request.getRole().equals(RoleType.RIDER.toString()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error("Role must be RIDER or DRIVER.", null));
        }
        UserResponseDTO user = registerService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<UserResponseDTO>success("User Registered", user));

    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@RequestBody @Valid LoginRequest request) {
        AuthResponse authResponse = authService.login(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.<AuthResponse>success("User Logged in", authResponse));
    }

}
