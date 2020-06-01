package com.utc.todo.controller;

import com.utc.todo.entity.Customer;
import com.utc.todo.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    private CustomerRepo customerRepo;

    @Autowired
    public RegisterController(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @PostMapping("/register")
    private String register(Customer customer, Model model) {
        System.out.println(customer);
        try {
            customerRepo.save(customer);
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
