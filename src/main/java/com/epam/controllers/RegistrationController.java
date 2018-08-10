package com.epam.controllers;

import com.epam.model.UserForm;
import com.epam.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    private RegisterService registerService;

    @GetMapping("/registration")
    public String getRegistrationPage() {
        return "register";
    }

    @PostMapping("/registration")
    public String registerUser(UserForm userForm) {
        registerService.registerUser(userForm);
        return "redirect:/login";
    }
}
