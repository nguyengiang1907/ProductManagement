package com.example.product_management.controller;

import com.example.product_management.model.User;
import com.example.product_management.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("users")
public class UserController {
    @Autowired
    private IUserService iUserService;
    @GetMapping("/signUp")
    public ModelAndView formSignUp(){
        ModelAndView modelAndView = new ModelAndView("/user/signUp");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }
    @PostMapping("/signUp")
    public ModelAndView saveUser(@ModelAttribute("user") User user){
        ModelAndView modelAndView = new ModelAndView("/user/login");
        iUserService.save(user);
        return modelAndView;
    }
    @GetMapping()
    public ModelAndView formLogin(){
        ModelAndView modelAndView = new ModelAndView("/user/login");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

}

