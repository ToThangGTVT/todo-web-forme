package com.utc.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value = "/home")
    private String showHome(Model model){
        return "index";
    }

    @GetMapping(value = "/")
    private String showHomeMain(Model model){
        return "index";
    }
}

