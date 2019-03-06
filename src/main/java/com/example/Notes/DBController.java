package com.example.Notes;

import com.example.Notes.Notes;
import com.example.Notes.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;

@Controller
@RequestMapping(path="/res")
public class DBController {
   // @Autowired
    private NotesRepository notesRepository;

    @GetMapping(path="/add")
    public @ResponseBody String addNewNote(@RequestParam Date date, @RequestParam String note_text){
        Notes note = new Notes();
        note.setDate(date);
        note.setNote_text(note_text);
        notesRepository.save(note);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Notes> getAllNotes(){
        return notesRepository.findAll();
    }
}
