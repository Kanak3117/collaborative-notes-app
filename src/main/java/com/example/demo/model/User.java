package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username cannot be empty!")
    @Size(min = 3, max = 20,
            message = "Username must be 3-20 characters!")
    @Column(unique = true, nullable = false)
    private String username;

    @NotBlank(message = "Email cannot be empty!")
    @Email(message = "Invalid email format!")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "Password cannot be empty!")
    @Size(min = 6,
            message = "Password must be at least 6 characters!")
    @Column(nullable = false)
    private String password;

    private String role = "USER";
}