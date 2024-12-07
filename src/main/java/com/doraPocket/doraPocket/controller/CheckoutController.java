
package com.doraPocket.doraPocket.controller;

import com.doraPocket.doraPocket.service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    private final CartService cartService;

    public CheckoutController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public String getCheckoutPage(Model model) {
        // Add cart items to the model for rendering in the Thymeleaf template
        model.addAttribute("cartItems", cartService.getAllCartItems());
        model.addAttribute("totalPrice", cartService.calculateTotalPrice());
        return "checkout";
    }

    @GetMapping("/login-register")
    public String showLoginRegisterPage() {
        return "login-register";
    }
}
