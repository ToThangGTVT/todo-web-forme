package com.utc.todo.controller;

import com.utc.todo.entity.Customer;
import com.utc.todo.exception.DateInputException;
import com.utc.todo.service.CustomerService;
import com.utc.todo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.Date;

@Controller
public class HomeController {

    private PostService postService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    public HomeController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(value = "/")
    private String showHomeMain(Model model) {
        return "index";
    }

    @PostMapping("/")
    private String postData(@RequestParam(required = false) String content,
                            @RequestParam(required = false) String strBegin,
                            @RequestParam(required = false) String strEnd,
                            HttpServletRequest request)
            throws ParseException, DateInputException {

        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        Customer customer = customerService.getByUsername(username);

        postService.save(content, strBegin, strEnd, customer);
        return "index";
    }
}

