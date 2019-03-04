package com.example.Notes;

import org.springframework.data.repository.CrudRepository;
import com.example.Notes.Notes;
import org.springframework.stereotype.Component;


public interface NotesRepository extends CrudRepository<Notes, Integer> {

}
