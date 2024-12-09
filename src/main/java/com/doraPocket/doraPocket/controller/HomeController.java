package com.doraPocket.doraPocket.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.doraPocket.doraPocket.model.User;
import com.doraPocket.doraPocket.service.CartService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final CartService cartService;

    public HomeController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/")
    public String home(HttpSession session, Model model) {
        // Retrieve the logged-in user from the session
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            // Count the total products in the cart for the logged-in user
            int totalProducts = cartService.countTotalProductsByUserId(loggedInUser.getId());
            double totalPrice = cartService.calculateTotalPriceByUserId(loggedInUser.getId());
            model.addAttribute("totalProducts", totalProducts);
            model.addAttribute("totalPrice", totalPrice);
        } else {
            model.addAttribute("totalProducts", 0); // Default value if no user is logged in
            model.addAttribute("totalPrice", 0); // Default value if no user is logged in
        }

        return "index"; // This refers to the "index.html" in the templates folder
    }
}
