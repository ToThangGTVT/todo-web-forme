package com.utc.todo.controller;

import com.utc.todo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("login")
    private String showLogin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return "login";
    }
}
