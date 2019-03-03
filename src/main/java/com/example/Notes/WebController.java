package com.example.Notes;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class WebController {

    @GetMapping("/webController")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="Just") String name, Model model) {
        model.addAttribute("name", name);
        return "webController";
    }

    @GetMapping("/")
    public String Login(Model model) {
        model.addAttribute("Login", new Login());
        return "Login";
    }

    @PostMapping("/Login")
    public String LoginSubmit(@ModelAttribute Login login) {
        return "result";
    }


    @RequestMapping("/Result")
    public String Result(@Valid @ModelAttribute("employee")Login login, BindingResult result, ModelMap model){
        model.addAttribute("username",login.getUsername());
        model.addAttribute("password",login.getPassword());

        return "Result";
    }
}