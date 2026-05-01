package com.example.demo.service;

import com.example.demo.dto.NoteRequest;
import com.example.demo.model.Note;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class NoteService {
    private final Map<Long, Note> notes = new ConcurrentHashMap<>();
    private final AtomicLong idSequence = new AtomicLong(0);

    public List<Note> findAll() {
        List<Note> sorted = new ArrayList<>(notes.values());
        sorted.sort((a, b) -> b.getCreatedAt().compareTo(a.getCreatedAt()));
        return Collections.unmodifiableList(sorted);
    }

    public Note findById(Long id) {
        Note note = notes.get(id);
        if (note == null) {
            throw new NoSuchElementException("Note not found: " + id);
        }
        return note;
    }

    public Note create(NoteRequest request) {
        Long id = idSequence.incrementAndGet();
        Note note = new Note(id, request.getTitle(), request.getContent(), Instant.now());
        notes.put(id, note);
        return note;
    }

    public Note update(Long id, NoteRequest request) {
        Note existing = findById(id);
        existing.setTitle(request.getTitle());
        existing.setContent(request.getContent());
        return existing;
    }

    public void delete(Long id) {
        if (notes.remove(id) == null) {
            throw new NoSuchElementException("Note not found: " + id);
        }
    }

    public void deleteAll() {
        notes.clear();
    }
}