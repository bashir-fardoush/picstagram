package com.fardoushlab.picstagram.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    @GetMapping("/")
    public String getHomePage(Model model){

        model.addAttribute("message","Welcome to this project");
        return "index";
    }
}
