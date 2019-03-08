package com.example.Notes;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class WebController {

    @GetMapping("/")
    public String Login(Model model) {
        model.addAttribute("Login", new Login());
        model.addAttribute("InsertNote", new InsertNote());
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


    @RequestMapping(value = "/InsertNote", method = RequestMethod.GET)
    public String InsertNote(@ModelAttribute("InsertNote")InsertNote note){
        return "InsertNote";
    }
    @PostMapping("/InsertNote")
    public String ResInsert(){
        return "ShowNotes";
    }
    @RequestMapping("/ShowNotes")
    public String ShowNotes(@Valid @ModelAttribute("InsertNote")InsertNote note, BindingResult result, ModelMap model){
        model.addAttribute("date",note.getDate());
        model.addAttribute("note_text",note.getNote_text());

        return "ShowNotes";
    }
}