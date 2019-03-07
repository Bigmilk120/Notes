package com.example.Notes;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import com.example.Notes.Notes;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

public interface NotesRepository extends CrudRepository<Notes, Integer> {

}