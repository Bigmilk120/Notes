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

    /* Instance to fill when user is logging into the system. Need it, to set owner of the notes. */
    User user = new User();

    /* Variable describes if user is logged or not. */
    boolean logged = false;

    @Autowired
    private NotesRepository notesRepository;
    @Autowired
    private LoginRepository loginRepository;

    @GetMapping("/")
    public String Login(Model model) {
        /* Model attribute which is needed with the form */
        model.addAttribute("Login", new Login());
        return "Login";
    }

    @RequestMapping("/Result")
    public String Result(@Valid @ModelAttribute("Login")Login login, ModelMap model){
        /* Model attribute which is needed with the forms */
        model.addAttribute("Notes",new Notes());
        model.addAttribute("Login", new Login());
        model.addAttribute("username",login.getUsername());
        model.addAttribute("password",login.getPassword());
        model.addAttribute("Filter", new Notes());

        /* List logins is a list of users with the same username and password which was given by user when logging. Can have 1 or 0 elements. */
        List<Login> logins = loginRepository.isCorrect(login.getUsername(),login.getPassword());

        /* Checking if user is already logged into the system. If not, return /Index to let him try one more time. */
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
        /* Model attribute which is needed with the form in /InsertNote */
        model.addAttribute("Notes", new Notes());
        return "InsertNote";
    }

    @PostMapping("/InsertNote")
    public String InsertNoteSubmit(@ModelAttribute Notes note){
        return "ShowNotes";
    }

    @RequestMapping("/ShowNotes")
    public String ShowNotes(@Valid @ModelAttribute("Notes")Notes note, BindingResult result, ModelMap model){
        /* Model attribute which is needed with the form in /ShowNotes */
        model.addAttribute("date",note.getDate());
        model.addAttribute("note_text",note.getNote_text());
        note.setOwner(user.getUsername());
        notesRepository.save(note);
        List<Notes> notes = notesRepository.showAllNotes(user.getUsername());
        return "ShowNotes";
    }
    @GetMapping("/ShowAll")
    public String showAll(Model model, @RequestParam(value="date", required=false)Date date) {
        /* Model attribute which is needed with the form in /ShowAll */
        model.addAttribute("Notes",new Notes());
        model.addAttribute("NotesUser", notesRepository.showAllNotes(user.getUsername()));
        if(date!=null){
            System.out.println("jestem!");
        }
        return "/ShowAll";
    }
    /* This function is used when user is filtering notes. */
    @PostMapping("/ShowAll")
    public String showAllPost(Model model, @RequestParam(value="date", required=false)Date date){
        /* Model attribute which is needed with the form */
        model.addAttribute("Notes",new Notes());
        model.addAttribute("NotesUser",notesRepository.showAllNotesDate(user.getUsername(), date.toString()));
        return "/ShowAll";
    }

    @GetMapping("/Register")
    public String Register(Model model) {
        /* Model attribute which is needed with the form */
        model.addAttribute("Login", new Login());
        return "Register";
    }

    @PostMapping("/Register")
    public String RegisterSubmit(@ModelAttribute Login login){
        return "AfterRegister";
    }

    @RequestMapping("/AfterRegister")
    public String AfterRegister(@Valid @ModelAttribute("Login")Login login, BindingResult result, ModelMap model){
         /* Saving user into the database */
        loginRepository.save(login);
        return "AfterRegister";
    }
    @RequestMapping("/Logout")
    public String logout(ModelMap model){
        model.addAttribute("Notes",new Notes());
        model.addAttribute("Login", new Login());
        logged=false;
        user=new User();
        return "/Index";
    }
}