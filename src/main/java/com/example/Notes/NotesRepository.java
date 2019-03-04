package com.example.Notes;

import org.springframework.data.repository.CrudRepository;
import com.example.Notes.Notes;

public interface NotesRepository extends CrudRepository<Notes, Integer> {

}
