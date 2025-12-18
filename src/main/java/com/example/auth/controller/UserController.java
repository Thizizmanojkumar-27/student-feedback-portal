package com.example.auth.controller;

import com.example.auth.model.User;
import com.example.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    // Accessible only after login (JWT required)
    @GetMapping("/profile")
    public User profile(Principal p) {
        return service.findByUsername(p.getName());
    }
}
