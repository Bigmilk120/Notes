package com.example.Notes;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {

    @GetMapping("/webController")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="Just") String name, Model model) {
        model.addAttribute("name", name);
        return "webController";
    }

    @GetMapping("/")
    public String index(@RequestParam(name="name", required=false, defaultValue="Krzysiek") String name, Model model){
        model.addAttribute("name", name);
        return "index";
    }
}