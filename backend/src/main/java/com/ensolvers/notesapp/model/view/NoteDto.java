package com.ensolvers.notesapp.model.view;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

import static com.ensolvers.notesapp.util.MessageConstants.*;

@Data
@Builder
public class NoteDto {
    private Long id;
    @NotBlank(message = TITLE_NOT_BLANK)
    private String title;
    private String content;
    private boolean archived;
    private List<String> tags;
    private LocalDate editedAt;
}
