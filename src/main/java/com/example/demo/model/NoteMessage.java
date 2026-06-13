package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NoteMessage {

    private Long noteId;        // Kaunsi note edit ho rahi hai
    private String content;     // Kya likha
    private String username;    // Kisne likha
    private String type;        // "EDIT", "JOIN", "LEAVE"
}