package com.mobility.user_service.controller;

import com.mobility.user_service.dto.UserRequestDTO;
import com.mobility.user_service.dto.UserResponseDTO;
import com.mobility.user_service.service.IUserService;
import com.mobility.user_service.dto.ApiResponse;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {
    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserResponseDTO>> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO){
        UserResponseDTO userResponse = userService.saveUser(userRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.<UserResponseDTO>success("User Created successfully", userResponse));

    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ApiResponse<UserResponseDTO>> getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email).map(user -> ResponseEntity.ok(ApiResponse.<UserResponseDTO>success("User found", user)))
                                                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.<UserResponseDTO>error("User not found",null)));

    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ApiResponse<UserResponseDTO>> getUserByEmail(@PathVariable Long id) {
        return userService.getUserById(id).map(user -> ResponseEntity.ok(ApiResponse.<UserResponseDTO>success("User found", user)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.<UserResponseDTO>error("User not found",null)));

    }
}
