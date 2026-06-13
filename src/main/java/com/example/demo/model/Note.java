package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "notes")
@Data
@NoArgsConstructor
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title cannot be empty!")
    @Size(min = 1, max = 100,
            message = "Title must be between 1-100 characters!")
    private String title;

    @NotBlank(message = "Content cannot be empty!")
    @Size(max = 5000,
            message = "Content cannot exceed 5000 characters!")
    private String content;

    @NotBlank(message = "Author cannot be empty!")
    private String author;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
}