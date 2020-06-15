package com.utc.todo.controller;

import com.utc.todo.config.RestFB;
import com.utc.todo.entity.Customer;
import com.utc.todo.exception.EmailExitsException;
import com.utc.todo.exception.UsernameExitsException;
import com.utc.todo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class RegisterController {

    private final CustomerService customerService;
    private Customer customer;

    @Autowired
    public RegisterController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/register")
    private String register(Customer customer, Model model) throws EmailExitsException, UsernameExitsException {
        this.customer = customer;
        customerService.save(customer);
        return "redirect:/login";
    }

    @GetMapping("/register")
    private String showForm1(Model model) {
        model.addAttribute("customer", new Customer());
        return "register";
    }

    @ExceptionHandler(value = EmailExitsException.class)
    private ModelAndView exceptionEmailExits(Model model){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("register");
        model.addAttribute("customer", this.customer);
        mav.addObject("email", "err");
        return mav;
    }

    @ExceptionHandler(value = UsernameExitsException.class)
    private ModelAndView exceptionUsernameExits(Model model){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("register");
        model.addAttribute("customer", this.customer);
        mav.addObject("username", "err");
        return mav;
    }
}
