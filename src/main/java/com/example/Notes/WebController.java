package com.example.Notes;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping("/")
    public String Login(@ModelAttribute Login login) {
        return "result";
    }
}