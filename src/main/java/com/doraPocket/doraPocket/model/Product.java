package com.doraPocket.doraPocket.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long id;
    private String name;
    private String category;
    private String imageUrl;
    private String sticker;
    private double price;
    private double oldPrice;
    private double discount;
    private String description;
}
