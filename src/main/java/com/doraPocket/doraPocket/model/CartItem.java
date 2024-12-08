package com.doraPocket.doraPocket.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;
    private String name;
    private String category;
    private String imageUrl;
    private double price;
    private int quantity;

    @Transient // This field is calculated, not stored in the database
    private double totalPrice;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public double getTotalPrice() {
        return this.price * this.quantity;
    }
}
