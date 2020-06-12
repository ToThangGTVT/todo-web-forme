package com.utc.todo.controller;

import com.utc.todo.entity.Customer;
import com.utc.todo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    private CustomerService customerService;

    @Autowired
    public RegisterController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/register")
    private String register(Customer customer, Model model) {
        try {
            customerService.save(customer);
        } catch (Exception e) {
            model.addAttribute("err", "err");
            return "register";
        }
        return "redirect:/login";
    }

    @GetMapping("/register")
    private String showForm1() {
        return "register";
    }
}
