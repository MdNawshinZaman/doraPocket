package com.doraPocket.doraPocket.service;

import com.doraPocket.doraPocket.model.Product;
import com.doraPocket.doraPocket.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.getAllProducts();
    }
}
