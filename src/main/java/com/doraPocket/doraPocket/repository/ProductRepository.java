package com.doraPocket.doraPocket.repository;

import com.doraPocket.doraPocket.model.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.List;

@Repository
public class ProductRepository {

    /**
     * Fetches all products from the JSON file.
     *
     * @return List of products
     */
    public List<Product> getAllProducts() {
        try {
            // Create an ObjectMapper instance for JSON parsing
            ObjectMapper mapper = new ObjectMapper();

            // Load the JSON file from resources
            InputStream inputStream = getClass().getResourceAsStream("/products.json");

            // Parse the JSON file into a list of Product objects
            return mapper.readValue(inputStream, new TypeReference<List<Product>>() {});
        } catch (Exception e) {
            // Throw a runtime exception if any error occurs
            throw new RuntimeException("Error reading products.json", e);
        }
    }
}
