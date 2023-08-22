package com.ensolvers.notesapp.mapper;

import com.ensolvers.notesapp.model.Note;
import com.ensolvers.notesapp.model.view.NoteDto;

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
                .tags(tagsToLowerCase(dto.getTags()))
                .build();
    }

    public static Note updateEntity (Note persistedNote, NoteDto dto){
        return Note.builder()
                .id(persistedNote.getId())
                .title(dto.getTitle() != null ? dto.getTitle() : persistedNote.getTitle())
                .content(dto.getContent() != null ? dto.getContent() : persistedNote.getContent())
                .tags(tagsToLowerCase(dto.getTags()))
                .build();
    }

    private static List<String> tagsToLowerCase(List<String> tags) {
        return tags.stream()
                .map(String::toLowerCase)
                .toList();
    }
}
