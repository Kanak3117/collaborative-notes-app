package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private BCryptPasswordEncoder passwordEncoder =
            new BCryptPasswordEncoder();

    // REGISTER
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest()
                    .body("Username already exists!");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest()
                    .body("Email already exists!");
        }
        user.setPassword(
                passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    // LOGIN - ab JWT token return karega!
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest request) {
        return userRepository
                .findByUsername(request.getUsername())
                .map(user -> {
                    if (passwordEncoder.matches(
                            request.getPassword(),
                            user.getPassword())) {
                        // JWT Token banao!
                        String token = jwtUtil.generateToken(
                                user.getUsername());
                        return ResponseEntity.ok(
                                new LoginResponse(token,
                                        user.getUsername()));
                    }
                    return ResponseEntity.badRequest()
                            .body("Wrong password!");
                })
                .orElse(ResponseEntity.badRequest()
                        .body("User not found!"));
    }

    @Data
    static class LoginRequest {
        private String username;
        private String password;
    }

    @Data
    static class LoginResponse {
        private String token;
        private String username;
        LoginResponse(String token, String username) {
            this.token = token;
            this.username = username;
        }
    }
}