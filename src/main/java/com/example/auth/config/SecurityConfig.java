package com.example.auth.config;

import com.example.auth.security.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.disable())
            .headers(h -> h.frameOptions(f -> f.disable()))
            .authorizeHttpRequests(auth -> auth

                // PUBLIC AUTH API
                .requestMatchers("/auth/login", "/auth/register").permitAll()

                // STATIC HTML FILES SHOULD ALWAYS BE PUBLIC
                .requestMatchers(
                        "/", "/login.html", "/register.html",
                        "/profile.html", "/admin/view-feedback.html"
                ).permitAll()

                // STATIC ASSETS
                .requestMatchers("/css/**", "/js/**", "/images/**", "/static/**").permitAll()

                // FEEDBACK SAVE FORM (PUBLIC)
                .requestMatchers("/feedback/save").permitAll()

                // USER API
                .requestMatchers("/api/user/**").hasRole("USER")

                // ADMIN API
                .requestMatchers("/api/admin/**", "/feedback/all").hasRole("ADMIN")

                .anyRequest().authenticated()
            );

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
