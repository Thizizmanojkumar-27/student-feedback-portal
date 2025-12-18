package com.example.auth.service;

import com.example.auth.dto.RegisterRequest;
import com.example.auth.model.User;

public interface UserService {
    User saveUser(RegisterRequest request);
    User findByUsername(String username);
}
