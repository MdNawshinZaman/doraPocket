package com.doraPocket.doraPocket.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doraPocket.doraPocket.model.CartItem;
import com.doraPocket.doraPocket.service.CartService;
import com.doraPocket.doraPocket.model.User;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "*")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<?> getCartItemsByUser(HttpSession session) {
        // Retrieve the logged-in user from the session
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return ResponseEntity.status(401).body("User not logged in");
        }

        // Fetch the cart items for the logged-in user
        List<CartItem> cartItems = cartService.getAllCartItemsByUserId(loggedInUser.getId());

        // Ensure total prices are calculated
        cartItems.forEach(item -> item.setTotalPrice(item.getTotalPrice()));

        // Return the cart items
        return ResponseEntity.ok(cartItems);
    }


    @PostMapping("/add")
    public ResponseEntity<?> addToCart(@RequestBody CartItem cartItem, HttpSession session) {
        // Check if the user is logged in
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return ResponseEntity.status(401).body("User not logged in");
        }
    
        // Set the user for the cart item
        cartItem.setUser(loggedInUser);
    
        // Proceed to add the item to the cart
        CartItem savedCartItem = cartService.addToCart(cartItem);
        return ResponseEntity.ok(savedCartItem);
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
