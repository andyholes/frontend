package com.ensolvers.notesapp.model.view;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class NoteDto {
    private Long id;
    private String title;
    private String content;
    private boolean archived;
    private List<String> tags;
    private LocalDate editedAt;
}
