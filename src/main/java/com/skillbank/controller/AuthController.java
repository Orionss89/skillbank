package com.skillbank.controller;

import com.skillbank.dto.RegisterDTO;
import com.skillbank.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterDTO dto) {
        userService.registerUser(dto);
        return ResponseEntity.ok("Rejestracja udana! Utworzono portfel.");
    }
}