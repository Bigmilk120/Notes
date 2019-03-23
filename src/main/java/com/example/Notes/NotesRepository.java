package com.example.Notes;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.example.Notes.Notes;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

public interface NotesRepository extends CrudRepository<Notes, Integer> {
    @Query("select n from Notes n where n.owner like ?1")
    List<Notes> showAllNotes(String owner);
    @Query("select n from Notes n where n.owner like ?1 and n.date like ?2")
    List<Notes> showAllNotesDate(String owner, String date);
    @Query("select n from Notes n where n.owner like ?1 and n.date between ?2 and ?3")
    List<Notes> showAllNotesDatesRange(String owner, String dateFrom, String dateTo);
}