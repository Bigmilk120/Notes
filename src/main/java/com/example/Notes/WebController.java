package com.example.Notes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.util.List;

@Controller
public class WebController {
    @Autowired
    private NotesRepository notesRepository;
    @Autowired
    private LoginRepository loginRepository;

    @GetMapping("/")
    public String Login(Model model) {
        model.addAttribute("Login", new Login());
        return "Login";
    }

    @RequestMapping("/Result")
    public String Result(@Valid @ModelAttribute("Login")Login login, BindingResult result, ModelMap model){
        model.addAttribute("Login", new Login());
        model.addAttribute("username",login.getUsername());
        model.addAttribute("password",login.getPassword());
        List<Login> logins = loginRepository.isCorrect(login.getUsername(),login.getPassword());
        if(logins.size()==1){
            return "Result";
        }
        return "/";
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
        List<Notes> notes = notesRepository.showAllNotes();
        for(Notes n : notes) {
            System.out.println(n.getId());
        }

        return "ShowNotes";
    }
    @GetMapping("/ShowAll")
    public String showAll(Model model) {
        model.addAttribute("Notes", notesRepository.findAll());
        return "/ShowAll";
    }
}