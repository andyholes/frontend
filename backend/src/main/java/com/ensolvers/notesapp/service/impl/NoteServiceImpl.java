package com.ensolvers.notesapp.service.impl;

import com.ensolvers.notesapp.exception.NotFoundException;
import com.ensolvers.notesapp.mapper.EntityMapper;
import com.ensolvers.notesapp.model.Note;
import com.ensolvers.notesapp.model.view.NoteDto;
import com.ensolvers.notesapp.repository.NoteRepository;
import com.ensolvers.notesapp.service.NoteService;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.ensolvers.notesapp.util.MessageConstants.NOTE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    @Override
    public List<NoteDto> getAll(Boolean isArchived, String tag) {
        return noteRepository.findAll(setFilters(isArchived, tag))
                .stream().map(EntityMapper::toDto).toList();
    }

    public static Specification<Note> setFilters(Boolean archived, String tag) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(archived != null) {
                predicates.add(criteriaBuilder.equal(root.get("archived"), archived));
            }
            if (tag != null && !tag.isEmpty()) {
                Join<Note, String> tags = root.join("tags");
                predicates.add(criteriaBuilder.like(tags, "%" + tag + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    @Override
    public NoteDto getById(Long id) {
        return EntityMapper.toDto(getNote(id));
    }

    @Override
    public NoteDto save(NoteDto dto) {
        Note note = EntityMapper.toEntity(dto);
        return EntityMapper.toDto(
                noteRepository.save(note));
    }

    @Override
    public NoteDto update(Long id, NoteDto dto) {
        Note persistedNote = getNote(id);
        Note updatedNote = noteRepository.save(EntityMapper.updateEntity(persistedNote, dto));
        return EntityMapper.toDto(updatedNote);
    }

    @Override
    public void delete(Long id) {
        noteRepository.delete(getNote(id));
    }

    @Override
    public void toggleArchived(Long id) {
        Note note = getNote(id);
        note.setArchived(!note.isArchived());
        noteRepository.save(note);
    }

    private Note getNote(Long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(NOTE_NOT_FOUND));
    }

    @Override
    public List<String> getAllTags() {
        return noteRepository.findAllTags();
    }
}
