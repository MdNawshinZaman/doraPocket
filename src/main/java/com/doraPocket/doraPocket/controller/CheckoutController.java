package com.doraPocket.doraPocket.controller;

import com.doraPocket.doraPocket.service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

import com.doraPocket.doraPocket.model.CartItem;
import com.doraPocket.doraPocket.model.User;

import java.util.List;



@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    private final CartService cartService;

    public CheckoutController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public String getCheckoutPage(Model model, HttpSession session) {
        // Get the logged-in user from the session
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            // Redirect to login page if no user is logged in
            return "redirect:/login-register";
        }

        // Fetch the cart items for the logged-in user
        List<CartItem> cartItems = cartService.getAllCartItemsByUserId(loggedInUser.getId());

        // Calculate the total price for the cart items
        double totalPrice = cartService.calculateTotalPriceByUserId(loggedInUser.getId());

        // Add data to the model for rendering in Thymeleaf
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalPrice", totalPrice);

        return "checkout"; // Render the checkout page
    }
}
