package com.doraPocket.doraPocket.service;

import com.doraPocket.doraPocket.model.CartItem;
import com.doraPocket.doraPocket.model.User;
import com.doraPocket.doraPocket.repository.CartItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartItemRepository cartItemRepository;

    public CartService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    /**
     * Retrieve all cart items.
     */
    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }


    public int countTotalProductsByUserId(Long userId) {
        return cartItemRepository.findByUserId(userId)
                                 .stream()
                                 .mapToInt(CartItem::getQuantity)
                                 .sum();
    }
    
    /**
     * Retrieve cart items for a specific user.
     */
    public List<CartItem> getAllCartItemsByUserId(Long userId) {
        return cartItemRepository.findByUserId(userId);
    }

    /**
     * Add a product to the cart.
     * If the product already exists for the user, update the quantity; otherwise, create a new cart item.
     */
    public CartItem addToCart(CartItem cartItem) {
        CartItem existingCartItem = cartItemRepository.findAll()
                .stream()
                .filter(item -> item.getProductId().equals(cartItem.getProductId()) &&
                                item.getUser().getId().equals(cartItem.getUser().getId()))
                .findFirst()
                .orElse(null);

        if (existingCartItem != null) {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + cartItem.getQuantity());
            return cartItemRepository.save(existingCartItem);
        }

        return cartItemRepository.save(cartItem);
    }

    /**
     * Add a product to the cart for a specific user by user ID.
     */
    public void addProductToCart(Long userId, Long productId, int quantity, double price) {
        CartItem existingCartItem = cartItemRepository.findAll()
                .stream()
                .filter(item -> item.getProductId().equals(productId) && item.getUser().getId().equals(userId))
                .findFirst()
                .orElse(null);

        if (existingCartItem != null) {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
            cartItemRepository.save(existingCartItem);
        } else {
            CartItem newCartItem = new CartItem();
            User user = new User();
            user.setId(userId);
            newCartItem.setUser(user); // Set the user with ID
            newCartItem.setProductId(productId);
            newCartItem.setQuantity(quantity);
            newCartItem.setPrice(price); // Set the product price
            cartItemRepository.save(newCartItem);
        }
    }

    /**
     * Remove a cart item by its ID.
     */
    public void removeFromCart(Long id) {
        cartItemRepository.deleteById(id);
    }


    /**
     * Remove all cart items for a specific user.
     */
    public void removeFromCartByUserId(Long userId) {
        List<CartItem> userCartItems = cartItemRepository.findByUserId(userId);
        cartItemRepository.deleteAll(userCartItems);
    }

    /**
     * Clear the entire cart (all users).
     */
    public void clearCart() {
        cartItemRepository.deleteAll();
    }

    /**
     * Clear the cart for a specific user.
     */
    public void clearCartByUserId(Long userId) {
        cartItemRepository.deleteAll(cartItemRepository.findByUserId(userId));
    }

    /**
     * Calculate the total price for all cart items (all users).
     */
    public double calculateTotalPrice() {
        return cartItemRepository.findAll()
                .stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
    }

    /**
     * Calculate the total price for a specific user's cart items.
     */
    public double calculateTotalPriceByUserId(Long userId) {
        return cartItemRepository.findByUserId(userId)
                .stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
    }


    
}
