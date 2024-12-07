package com.doraPocket.doraPocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginRegisterController {

    @GetMapping("/login-register")
    public String showLoginRegisterPage() {
        return "login-register"; // Refers to login-register.html in templates/
    }
}
