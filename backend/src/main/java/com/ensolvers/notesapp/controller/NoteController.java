package com.ensolvers.notesapp.controller;

import com.ensolvers.notesapp.model.view.NoteDto;
import com.ensolvers.notesapp.service.NoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notes")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@Slf4j
public class NoteController {

    private final NoteService noteService;

    @GetMapping
    public ResponseEntity<List<NoteDto>> getAll(@RequestParam(value = "archived", required = false) Boolean archived,
                                                @RequestParam(value = "tag", required = false) String tag) {
        return ResponseEntity.ok(noteService.getAll(archived, tag));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(noteService.getById(id));
    }

    @PostMapping
    public ResponseEntity<NoteDto> save(@RequestBody @Valid NoteDto dto) {
        NoteDto savedNote = noteService.save(dto);
        log.info("NOTE SAVED SUCCESSFULLY");
        return ResponseEntity.status(HttpStatus.CREATED).body(savedNote);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoteDto> update(@PathVariable @Valid Long id, @RequestBody NoteDto dto) {
        NoteDto updatedNote = noteService.update(id, dto);
        log.info("NOTE UPDATED SUCCESSFULLY");
        return ResponseEntity.ok(updatedNote);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        noteService.delete(id);
        log.info("NOTE DELETED SUCCESSFULLY");
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/toggle-archived/{id}")
    public ResponseEntity<Void> toggleArchived(@PathVariable Long id) {
        noteService.toggleArchived(id);
        log.info("NOTE ARCHIVE STATUS CHANGED SUCCESSFULLY");
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/tags")
    public ResponseEntity<List<String>> getAllTags(){
        return ResponseEntity.ok(noteService.getAllTags());
    }
}
