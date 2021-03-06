package com.utc.todo.controller;

import com.utc.todo.entity.Customer;
import com.utc.todo.entity.Post;
import com.utc.todo.exception.DateInputException;
import com.utc.todo.service.CustomerService;
import com.utc.todo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.function.EntityResponse;

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
        model.addAttribute("posts",postService.getAll(getCustomer()));
        return "index";
    }

    @PostMapping("/")
    private String postData(@RequestParam(required = false) String content,
                            @RequestParam(required = false) String title,
                            @RequestParam(required = false) String strBegin,
                            @RequestParam(required = false) String strEnd,
                            Model model)
            throws ParseException, DateInputException {
        Customer customer = getCustomer();
        postService.save(content, strBegin, strEnd, customer, title);
        return "redirect:";
    }

    private Customer getCustomer(){
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        return customerService.getByUsername(username);
    }

    @GetMapping("/api/change/done")
    private String changeStatusDone(@RequestParam("id") int id) {
        System.out.println("x");
        Post post = postService.getByIdAndCustomer(id, getCustomer());
        post.setStatus("1");
        postService.save(post);
        return "redirect:/";
    }

    @GetMapping("/api/change/undone")
    private String changeStatusUndone(@RequestParam("id") int id) {
        System.out.println("x");
        Post post = postService.getByIdAndCustomer(id, getCustomer());
        post.setStatus("0");
        postService.save(post);
        return "redirect:/";
    }
}

