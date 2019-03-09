package com.example.Notes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;

@Controller
public class WebController {
    @Autowired
    private NotesRepository notesRepository;

    @GetMapping("/")
    public String Login(Model model) {
        model.addAttribute("Login", new Login());
       // model.addAttribute("InsertNote", new InsertNote());
        return "Login";
    }

    @PostMapping("/Login")
    public String LoginSubmit(@ModelAttribute Login login) {
        return "Result";
    }

    @RequestMapping("/Result")
    public String Result(@Valid @ModelAttribute("note")Login login, BindingResult result, ModelMap model){
        model.addAttribute("username",login.getUsername());
        model.addAttribute("password",login.getPassword());

        return "Result";
    }

    @GetMapping("/InsertNote")
    public String InsertNote(Model model) {
        model.addAttribute("Notes", new Notes());
        return "InsertNote";
    }

    @PostMapping("/InsertNote")
    public String InsertNoteSubmit(@ModelAttribute Notes note){
        return "ShowNotes";
    }
    @RequestMapping("/ShowNotes")
    public String ShowNotes(@Valid @ModelAttribute("Notes")Notes note, BindingResult result, ModelMap model){
        model.addAttribute("date",note.getDate());
        model.addAttribute("note_text",note.getNote_text());
        notesRepository.save(note);
        System.out.println("wywołane!");
        return "ShowNotes";
    }
}