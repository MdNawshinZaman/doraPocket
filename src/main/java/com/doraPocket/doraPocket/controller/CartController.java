package com.doraPocket.doraPocket.controller;

import com.doraPocket.doraPocket.model.CartItem;
import com.doraPocket.doraPocket.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "*")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<List<CartItem>> getAllCartItems() {
        List<CartItem> cartItems = cartService.getAllCartItems();
        cartItems.forEach(item -> item.setTotalPrice(item.getTotalPrice())); // Ensure total price is calculated
        return ResponseEntity.ok(cartItems);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addToCart(@RequestBody CartItem cartItem, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return ResponseEntity.status(401).body("User not logged in");
        }
        return ResponseEntity.ok(cartService.addToCart(cartItem));
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Void> removeFromCart(@PathVariable Long id) {
        cartService.removeFromCart(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/clear")
    public ResponseEntity<Void> clearCart() {
        cartService.clearCart();
        return ResponseEntity.noContent().build();
    }
}
