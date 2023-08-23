package com.ensolvers.notesapp.util;

import com.ensolvers.notesapp.model.view.NoteDto;
import com.ensolvers.notesapp.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DatabaseSeeder implements ApplicationRunner {
    private final NoteService noteService;
    @Override
    public void run(ApplicationArguments args) {
        if (noteService.getAll(null, null).isEmpty()) {
            noteService.save(NoteDto.builder()
                    .title("Important information")
                    .content("Some important information that I need to remember all the time: \n - Credentials for my AWS account: ... \n - Holiday home address: ...")
                    .archived(false)
                    .tags(Arrays.asList("information", "daily", "frequently used"))
                    .build());

            noteService.save(NoteDto.builder()
                    .title("Shopping list")
                    .content("This is an example of a shopping list that can be persisted in this app :)")
                    .archived(false)
                    .tags(Arrays.asList("shopping", "food"))
                    .build());

            noteService.save(NoteDto.builder()
                    .title("Another important note")
                    .content("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Nisl tincidunt eget nullam non. Quis hendrerit dolor magna eget est lorem ipsum dolor sit.")
                    .archived(false)
                    .tags(Arrays.asList("lorem ipsum", "coding"))
                    .build());

            noteService.save(NoteDto.builder()
                    .title("Some old note")
                    .content("This is an old note, so it was archived")
                    .archived(true)
                    .tags(List.of("old"))
                    .build());

            noteService.save(NoteDto.builder()
                    .title("What is React.js?")
                    .content("React.js is a front-end library that has gradually become the go-to framework for modern web development within the JavaScript community.")
                    .archived(false)
                    .tags(Arrays.asList("coding", "react", "developer"))
                    .build());
        }
    }
}
