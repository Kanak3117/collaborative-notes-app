package com.example.demo.controller;

import com.example.demo.model.Note;
import com.example.demo.repository.NoteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    private NoteRepository noteRepository;

    // Sab notes lao
    @GetMapping
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    // Nayi note banao — with validation
    @PostMapping
    public ResponseEntity<?> createNote(
            @RequestBody Note note) {

        Map<String, String> errors = new HashMap<>();

        // Manual validation
        if (note.getTitle() == null ||
                note.getTitle().trim().isEmpty()) {
            errors.put("title", "Title cannot be empty!");
        }
        if (note.getContent() == null ||
                note.getContent().trim().isEmpty()) {
            errors.put("content", "Content cannot be empty!");
        }
        if (note.getAuthor() == null ||
                note.getAuthor().trim().isEmpty()) {
            errors.put("author", "Author cannot be empty!");
        }

        // Errors hain toh 400 return karo
        if (!errors.isEmpty()) {
            return ResponseEntity.badRequest().body(errors);
        }

        // Sab theek hai toh save karo
        return ResponseEntity.ok(noteRepository.save(note));
    }

    // ID se note lao
    @GetMapping("/{id}")
    public ResponseEntity<?> getNoteById(
            @PathVariable Long id) {
        return noteRepository.findById(id)
                .map(note -> ResponseEntity.ok(note))
                .orElse(ResponseEntity.notFound().build());
    }

    // Note update karo
    @PutMapping("/{id}")
    public ResponseEntity<?> updateNote(
            @PathVariable Long id,
            @Valid @RequestBody Note updatedNote,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errors.put(
                            error.getField(),
                            error.getDefaultMessage()
                    )
            );
            return ResponseEntity.badRequest().body(errors);
        }

        return noteRepository.findById(id)
                .map(note -> {
                    note.setTitle(updatedNote.getTitle());
                    note.setContent(updatedNote.getContent());
                    note.setAuthor(updatedNote.getAuthor());
                    return ResponseEntity.ok(
                            noteRepository.save(note));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Note delete karo
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNote(
            @PathVariable Long id) {
        return noteRepository.findById(id)
                .map(note -> {
                    noteRepository.deleteById(id);
                    return ResponseEntity.ok()
                            .body("Note delete ho gayi!");
                })
                .orElse(ResponseEntity.notFound().build());
    }
}