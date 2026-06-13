package com.example.demo.controller;

import com.example.demo.model.NoteMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class NoteWebSocketController {

    // Client /app/note.edit par message bheje
    // Server /topic/note/{noteId} par broadcast kare
    @MessageMapping("/note.edit")
    @SendTo("/topic/notes")
    public NoteMessage editNote(NoteMessage message) {
        message.setType("EDIT");
        return message;
    }

    // User join hua
    @MessageMapping("/note.join")
    @SendTo("/topic/notes")
    public NoteMessage joinNote(NoteMessage message) {
        message.setType("JOIN");
        message.setContent(
                message.getUsername() + " joined!");
        return message;
    }

    // User chala gaya
    @MessageMapping("/note.leave")
    @SendTo("/topic/notes")
    public NoteMessage leaveNote(NoteMessage message) {
        message.setType("LEAVE");
        message.setContent(
                message.getUsername() + " left!");
        return message;
    }
}
