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
        //model.addAttribute("Notes", notesRepository.findAll());
        List<Login> logins = loginRepository.isCorrect(login.getUsername(),login.getPassword());
        if(logins.size()==1){
            user.setUsername(login.getUsername());
            model.addAttribute("NotesUser", notesRepository.showAllNotes(user.getUsername()));
            String str="2019-03-07";
            Date temp=Date.valueOf(str);
            List<Notes> dayNotes = notesRepository.showAllNotesDate(user.getUsername(), str);
            System.out.println(temp);
            for(int i=0;i<dayNotes.size();i++){
                System.out.println(dayNotes.get(i).getNote_text());
            }
            System.out.println("Koniec");
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
    public String showAll(Model model) {
        model.addAttribute("NotesUser", notesRepository.showAllNotes(user.getUsername()));
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