package com.example.auth.service;

import com.example.auth.dto.RegisterRequest;
import com.example.auth.model.User;
import com.example.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public User saveUser(RegisterRequest req) {

        User u = new User();
        u.setUsername(req.username);
        u.setEmail(req.email);
        u.setPassword(encoder.encode(req.password));

        // --- NEW: ROLE SUPPORT ---
        if (req.role != null && 
            (req.role.equalsIgnoreCase("ADMIN") || req.role.equalsIgnoreCase("USER"))) {

            u.setRole(req.role.toUpperCase());

        } else {
            u.setRole("USER");  // default role
        }
        // --------------------------

        try {
            return repo.save(u);

        } catch (DataIntegrityViolationException e) {

            // MySQL duplicate key
            if (e.getCause() != null && e.getCause().getMessage().contains("Duplicate entry")) {
                throw new RuntimeException("Email or Username already exists");
            }

            throw new RuntimeException("Registration failed. Try again.");
        }
    }

    @Override
    public User findByUsername(String username) {
        return repo.findByUsername(username);
    }
}
