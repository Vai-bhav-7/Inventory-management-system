package org.example.inventorymanagementsystem.controller;

import org.example.inventorymanagementsystem.kafka.ProductEvent;
import org.example.inventorymanagementsystem.kafka.ProductEventProducer;
import org.example.inventorymanagementsystem.repository.productRepository;
import org.example.inventorymanagementsystem.model.product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import jakarta.validation.Valid;
import org.example.inventorymanagementsystem.dto.productRequest;
import org.example.inventorymanagementsystem.Exception.ProductNotFoundException;
import org.example.inventorymanagementsystem.Service.ProductService;
import org.springframework.cache.annotation.CacheEvict;



import java.util.List;

@RestController
public class productController {

    private final productRepository productRepository;
    private final ProductService productService;
    private final ProductEventProducer productEventProducer;

    public productController(productRepository productRepository,
                             ProductService productService,
                             ProductEventProducer productEventProducer) {

        this.productRepository = productRepository;
        this.productService = productService;
        this.productEventProducer = productEventProducer;
    }

    @GetMapping("/product")
    public product getProduct() {
        return new product(
                1L,
                "Laptop",
                55000,
                10,
                "Electronics"
        );
    }

    @PostMapping("/product")
    public product createProduct(@Valid @RequestBody productRequest request) {

        product product = new product();

        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setCategory(request.getCategory());

        product savedProduct = productRepository.save(product);

        ProductEvent event = new ProductEvent(
                "PRODUCT_CREATED",
                savedProduct.getId(),
                savedProduct.getName(),
                savedProduct.getQuantity()
        );

        productEventProducer.sendProductCreatedEvent(event);

        return savedProduct;
    }

    @GetMapping("/products")
    public List<product> getProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/product/{id}")
    public product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);

    }

    @CacheEvict(value = "products", key = "#id")
    @PutMapping("/product/{id}")
    public product updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody productRequest request) {

        product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product with ID " + id + " not found"
                        )
                );

        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setCategory(request.getCategory());

        return productRepository.save(product);
    }

    @CacheEvict(value = "products", key = "#id")
    @DeleteMapping("/product/{id}")
    public String deleteProduct(@PathVariable Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(
                    "Product with ID " + id + " not found"
            );
        }

        productRepository.deleteById(id);

        return "Product with ID " + id + " deleted successfully";
    }
}