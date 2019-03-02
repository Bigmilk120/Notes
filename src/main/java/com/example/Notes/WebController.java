package com.example.Notes;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String Result(@RequestParam(name="username", required=false, defaultValue="Just") String username,@RequestParam(name="password", required=false, defaultValue="Just") String password){
        return "result";
    }
}