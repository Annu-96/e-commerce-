package com.example.demo.controller;

import com.example.demo.dto.NoteRequest;
import com.example.demo.dto.NoteResponse;
import com.example.demo.model.Note;
import com.example.demo.service.NoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public List<NoteResponse> list() {
        return noteService.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public NoteResponse get(@PathVariable Long id) {
        return toResponse(noteService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NoteResponse create(@RequestBody NoteRequest request) {
        return toResponse(noteService.create(request));
    }

    @PutMapping("/{id}")
    public NoteResponse update(@PathVariable Long id, @RequestBody NoteRequest request) {
        return toResponse(noteService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        noteService.delete(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll() {
        noteService.deleteAll();
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNotFound(NoSuchElementException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    private NoteResponse toResponse(Note note) {
        return new NoteResponse(note.getId(), note.getTitle(), note.getContent(), note.getCreatedAt());
    }
}