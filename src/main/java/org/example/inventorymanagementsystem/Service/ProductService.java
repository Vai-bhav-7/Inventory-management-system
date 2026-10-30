package org.example.inventorymanagementsystem.Service;

import org.example.inventorymanagementsystem.model.product;
import org.example.inventorymanagementsystem.repository.productRepository;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;

@Service
public class ProductService {

    private final productRepository productRepository;

    public ProductService(productRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Cacheable(value = "products", key = "#id")
    public product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product with ID " + id + " not found"));
    }
}