package com.ensolvers.notesapp.service;

import com.ensolvers.notesapp.model.view.NoteDto;

import java.util.List;

public interface NoteService {
    List<NoteDto> getAll(Boolean isArchived, String tag);

    NoteDto getById(Long id);

    NoteDto save(NoteDto dto);

    NoteDto update(Long id, NoteDto dto);

    void delete(Long id);

    void toggleArchived(Long id);

    List<String> getAllTags();
}
