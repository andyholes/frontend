package com.ensolvers.notesapp.mapper;

import com.ensolvers.notesapp.model.Note;
import com.ensolvers.notesapp.model.view.NoteDto;

import java.util.ArrayList;
import java.util.List;

public class EntityMapper {
    public static NoteDto toDto(Note entity){
        return NoteDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .archived(entity.isArchived())
                .tags(entity.getTags())
                .editedAt(entity.getEditedAt())
                .build();
    }

    public static Note toEntity (NoteDto dto){
        return Note.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .archived(dto.isArchived())
                .tags(normalizeTags(dto.getTags()))
                .build();
    }

    public static Note updateEntity (Note persistedNote, NoteDto dto){
        return Note.builder()
                .id(persistedNote.getId())
                .title(dto.getTitle() != null ? dto.getTitle() : persistedNote.getTitle())
                .content(dto.getContent() != null ? dto.getContent() : persistedNote.getContent())
                .archived(persistedNote.isArchived())
                .tags(normalizeTags(dto.getTags()))
                .build();
    }

    private static List<String> normalizeTags(List<String> tags) {
        return tags != null ? tags.stream()
                .map(String::toLowerCase)
                .map(s -> s.replaceAll(" ", "-"))
                .distinct()
                .toList() : new ArrayList<>();
    }
}
