package com.doraPocket.doraPocket.service;

import com.doraPocket.doraPocket.model.CartItem;
import com.doraPocket.doraPocket.repository.CartItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartItemRepository cartItemRepository;

    public CartService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    public CartItem addToCart(CartItem cartItem) {
        CartItem existingCartItem = cartItemRepository.findAll()
                .stream()
                .filter(item -> item.getProductId().equals(cartItem.getProductId()))
                .findFirst()
                .orElse(null);

        if (existingCartItem != null) {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + cartItem.getQuantity());
            return cartItemRepository.save(existingCartItem);
        }

        return cartItemRepository.save(cartItem);
    }

    public void removeFromCart(Long id) {
        cartItemRepository.deleteById(id);
    }

    public void clearCart() {
        cartItemRepository.deleteAll();
    }

    public double calculateTotalPrice() {
        return cartItemRepository.findAll()
                .stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
    }
}
