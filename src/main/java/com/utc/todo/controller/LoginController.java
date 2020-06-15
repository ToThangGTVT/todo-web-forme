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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class LoginController {

    private RestFB restFb;
    private CustomerService customerService;

    @Autowired
    public LoginController(CustomerService customerService, RestFB restFb) {
        this.customerService = customerService;
        this.restFb = restFb;
    }

    @GetMapping("login")
    private String showLogin(HttpServletRequest request) {
        return "login";
    }

    @RequestMapping("/oath")
    public String loginFacebook(HttpServletRequest request) throws IOException, UsernameExitsException, EmailExitsException {
        String code = request.getParameter("code");

        if (code == null || code.isEmpty()) {
            return "redirect:/login?facebook=error";
        }

        String accessToken = restFb.getToken(code);

        com.restfb.types.User user = restFb.getUserInfo(accessToken);
        UserDetails userDetail = restFb.buildUser(user);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetail,
                null,
                userDetail.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Customer customer = new Customer();
        customer.setUsername(user.getId());
        customer.setFirstName(user.getName());
        customerService.save(customer);
        return "redirect:/";
    }

    @ExceptionHandler(value = UsernameExitsException.class)
    private ModelAndView exceptionUsernameExits(Model model){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/");
        return mav;
    }
}
