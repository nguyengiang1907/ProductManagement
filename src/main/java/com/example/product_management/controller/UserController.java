package com.example.product_management.controller;

import com.example.product_management.model.User;
import com.example.product_management.service.user.IUserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserService iUserService;

    @GetMapping()
    public ModelAndView formLogin() {
        ModelAndView modelAndView = new ModelAndView("/user/login");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @GetMapping("/signUp")
    public ModelAndView formSignUp() {
        ModelAndView modelAndView = new ModelAndView("/user/signUp");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping("/signUp")
    public ModelAndView saveUser(@ModelAttribute("user") User user) {
        if (user.getPassword().equals(user.getConfirmPassword())) {
            if (iUserService.findUserByEmail(user.getEmail()) == null) {
                ModelAndView modelAndView = new ModelAndView("redirect:/users");
                iUserService.save(user);
                return modelAndView;
            }
        }
        return new ModelAndView("/user/signUp");
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user, HttpSession session) {
        User optional = iUserService.findUserByEmail(user.getEmail());
        if (optional != null) {
            session.setAttribute("user", optional);
            return "redirect:/products";
        } else {
            return "redirect:/users";
        }
    }
}
