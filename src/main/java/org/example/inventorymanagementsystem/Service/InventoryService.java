package org.example.inventorymanagementsystem.Service;

import org.example.inventorymanagementsystem.model.InventoryTransaction;
import org.example.inventorymanagementsystem.model.product;
import org.example.inventorymanagementsystem.model.TransactionType;
import org.example.inventorymanagementsystem.repository.InventoryTransactionRepository;
import org.example.inventorymanagementsystem.repository.productRepository;
import org.springframework.stereotype.Service;
import org.example.inventorymanagementsystem.Exception.InsufficientStockException;
import org.springframework.cache.annotation.CacheEvict;

import java.time.LocalDateTime;

@Service
public class InventoryService {

    private final productRepository productRepository;
    private final InventoryTransactionRepository transactionRepository;

    public InventoryService(
            productRepository productRepository,
            InventoryTransactionRepository transactionRepository) {

        this.productRepository = productRepository;
        this.transactionRepository = transactionRepository;
    }

    @CacheEvict(value = "products", key = "#productId")
    public InventoryTransaction stockIn(Long productId, int quantity) {

        product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        product.setQuantity(product.getQuantity() + quantity);
        productRepository.save(product);

        InventoryTransaction transaction = new InventoryTransaction();

        transaction.setProduct(product);
        transaction.setType(TransactionType.IN);
        transaction.setQuantity(quantity);
        transaction.setCreatedAt(LocalDateTime.now());

        return transactionRepository.save(transaction);
    }

    @CacheEvict(value = "products", key = "#productId")
    public InventoryTransaction stockOut(Long productId, int quantity) {

        product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        if (product.getQuantity() < quantity) {
            throw new InsufficientStockException("Insufficient stock");
        }

        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);

        InventoryTransaction transaction = new InventoryTransaction();

        transaction.setProduct(product);
        transaction.setType(TransactionType.OUT);
        transaction.setQuantity(quantity);
        transaction.setCreatedAt(LocalDateTime.now());

        return transactionRepository.save(transaction);
    }

}
