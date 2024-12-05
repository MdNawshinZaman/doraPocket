package com.doraPocket.doraPocket.controller;

import com.doraPocket.doraPocket.model.Product;
import com.doraPocket.doraPocket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    // API endpoint for fetching products
    @GetMapping("/api/products")
    public List<Product> getProducts() {
        return productService.getProducts();
    }
}
