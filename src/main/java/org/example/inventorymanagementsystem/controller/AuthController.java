package org.example.inventorymanagementsystem.controller;

import org.example.inventorymanagementsystem.dto.UserRequest;
import org.example.inventorymanagementsystem.model.User;
import org.example.inventorymanagementsystem.Service.UserService;
import org.springframework.web.bind.annotation.*;
import org.example.inventorymanagementsystem.dto.LoginRequest;
import org.example.inventorymanagementsystem.dto.LoginResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(@Valid @RequestBody UserRequest request) {
        return userService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {

    String token = userService.login(
            request.getUsername(),
            request.getPassword()
    );

    return new LoginResponse(token);
}
}
