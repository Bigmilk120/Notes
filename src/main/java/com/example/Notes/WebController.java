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

    User user = new User();
    boolean logged = false;

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
    public String Result(@Valid @ModelAttribute("Login")Login login, ModelMap model){
        model.addAttribute("Notes",new Notes());
        model.addAttribute("Login", new Login());
        model.addAttribute("username",login.getUsername());
        model.addAttribute("password",login.getPassword());
        //model.addAttribute("Notes", notesRepository.findAll());
        model.addAttribute("Filter", new Notes());
        List<Login> logins = loginRepository.isCorrect(login.getUsername(),login.getPassword());

        if(logins.size()==1|| logged == true){
            if(logged == false) {
                logged = true;
                user.setUsername(login.getUsername());
            }
            model.addAttribute("NotesUser", notesRepository.showAllNotes(user.getUsername()));
            return "ShowAll";
        }
        return "/Index";
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
        note.setOwner(user.getUsername());
        notesRepository.save(note);
        List<Notes> notes = notesRepository.showAllNotes(user.getUsername());
        return "ShowNotes";
    }
    @GetMapping("/ShowAll")
    public String showAll(Model model, @RequestParam(value="date", required=false)Date date) {
        System.out.println("jestem!");

        model.addAttribute("Notes",new Notes());
        model.addAttribute("NotesUser", notesRepository.showAllNotes(user.getUsername()));
        if(date!=null){
            System.out.println("jestem!");
        }
        return "/ShowAll";
    }
    @PostMapping("/ShowAll")
    public String showAllPost(Model model, @RequestParam(value="date", required=false)Date date){
        model.addAttribute("Notes",new Notes());
        model.addAttribute("NotesUser",notesRepository.showAllNotesDate(user.getUsername(), date.toString()));
        return "/ShowAll";
    }

    @GetMapping("/Register")
    public String Register(Model model) {
        model.addAttribute("Login", new Login());
        return "Register";
    }

    @PostMapping("/Register")
    public String RegisterSubmit(@ModelAttribute Login login){
        return "AfterRegister";
    }

    @RequestMapping("/AfterRegister")
    public String AfterRegister(@Valid @ModelAttribute("Login")Login login, BindingResult result, ModelMap model){
        loginRepository.save(login);
        return "AfterRegister";
    }
}