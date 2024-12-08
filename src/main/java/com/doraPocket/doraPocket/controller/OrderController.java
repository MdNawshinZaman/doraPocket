package com.doraPocket.doraPocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {
    @GetMapping("/order-confirmation")
    public String orderConfirmation() {
        return "order-confirmation"; // Thymeleaf will look for templates/order-confirmation.html
    }
}
