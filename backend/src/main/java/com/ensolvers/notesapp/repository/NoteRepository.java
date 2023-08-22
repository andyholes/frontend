package com.ensolvers.notesapp.repository;

import com.ensolvers.notesapp.model.Note;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findAll(Specification<Note> specification);

    @Query(value = "SELECT DISTINCT tags FROM notes JOIN note_tags", nativeQuery = true)
    List<String> findAllTags();
}
