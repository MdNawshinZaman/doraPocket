package com.doraPocket.doraPocket.controller;

import com.doraPocket.doraPocket.model.User;
import com.doraPocket.doraPocket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginRegisterController {

    private final UserService userService;

    @Autowired
    public LoginRegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login-register")
    public String showLoginRegisterPage() {
        return "login-register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        userService.registerUser(user);
        model.addAttribute("message", "User registered successfully!");
        return "redirect:/login-register"; // Redirect to login page
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String email, @RequestParam String password, Model model, HttpSession session) {
        User user = userService.loginUser(email, password);
        if (user != null) {
            // Store user information in the session
            session.setAttribute("loggedInUser", user);
            model.addAttribute("message", "Login successful!");
            return "redirect:/"; // Redirect to checkout or dashboard
        } else {
            model.addAttribute("error", "Invalid email or password!");
            return "login-register";
        }
    }

    @GetMapping("/logout")
    public String logoutUser(HttpSession session) {
        // Invalidate the session to log the user out
        session.invalidate();
        return "redirect:/"; // Redirect to login page
    }
}
