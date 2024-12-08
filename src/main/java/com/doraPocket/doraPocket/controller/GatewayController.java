package com.doraPocket.doraPocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GatewayController {
    @GetMapping("/gateway")
    public String gatewayPage() {
        return "gateway"; // Thymeleaf will look for templates/gateway.html
    }
}
