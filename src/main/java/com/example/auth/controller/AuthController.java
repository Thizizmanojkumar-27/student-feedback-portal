package com.example.auth.controller;

import com.example.auth.dto.*;
import com.example.auth.model.User;
import com.example.auth.service.UserService;
import com.example.auth.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.userdetails.UserDetails;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService service;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder encoder;

    // ===================== REGISTER =====================
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {

        try {
            User saved = service.saveUser(req);
            return ResponseEntity.ok(saved);

        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    // ===================== LOGIN =====================
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {

        User u = service.findByUsername(req.username);

        if (u == null) {
            return ResponseEntity.status(401).body("User Not Found");
        }

        if (!encoder.matches(req.password, u.getPassword())) {
            return ResponseEntity.status(401).body("Invalid Password");
        }

        // Build Spring Security UserDetails
        UserDetails userDetails =
                org.springframework.security.core.userdetails.User
                        .withUsername(u.getUsername())
                        .password(u.getPassword())
                        .roles(u.getRole())
                        .build();

        // IMPORTANT: Add ROLE inside JWT
        String token = jwtUtil.generateTokenWithRole(userDetails, u.getRole());

        return ResponseEntity.ok(new JwtResponse(token, u.getRole()));
    }
}
