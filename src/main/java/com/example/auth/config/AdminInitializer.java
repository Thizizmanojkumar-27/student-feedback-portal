//package com.example.auth.config;
//
//import com.example.auth.model.User;
//import com.example.auth.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@Component   // <-- REQUIRED
//public class AdminInitializer implements CommandLineRunner {
//
//    @Autowired
//    private UserRepository repo;
//
//    @Autowired
//    private PasswordEncoder encoder;
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        System.out.println("🔍 Checking for admin account…");
//
//        if (!repo.existsByUsername("admin")) {
//
//            User u = new User();
//            u.setUsername("admin");
//            u.setEmail("admin@gmail.com");
//            u.setPassword(encoder.encode("superadmin"));   // Admin password
//            u.setRole("ADMIN");
//
//            repo.save(u);
//
//            System.out.println("✅ DEFAULT ADMIN CREATED (username=admin, password=superadmin)");
//        } else {
//            System.out.println("✔ Admin already exists — skipping creation.");
//        }
//    }
//}
//
//
//
//
