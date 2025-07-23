package com.mobility.authservice.controller;

import com.mobility.authservice.dto.AuthResponse;
import com.mobility.authservice.dto.LoginRequest;
import com.mobility.authservice.dto.RegisterUserRequest;

import com.mobility.authservice.service.IAuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final IAuthService authService;

    @Autowired
    public AuthController(IAuthService authService) {
         this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid RegisterUserRequest request){
         AuthResponse response = authService.register(request);
         return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest request){
        String token = authService.login(request);
        return ResponseEntity.ok(new AuthResponse(token));
    }

}
