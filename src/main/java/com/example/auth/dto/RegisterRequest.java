package com.example.auth.dto;

public class RegisterRequest {
    public String username;
    public String email;
    public String password;

    // NEW FIELD for role-based access
    public String role = "USER";   // default role = USER
}
