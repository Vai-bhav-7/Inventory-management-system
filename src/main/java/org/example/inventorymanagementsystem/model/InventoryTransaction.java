package org.example.inventorymanagementsystem.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class InventoryTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private product product;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private int quantity;

    private LocalDateTime createdAt;

    public InventoryTransaction() {
    }

    public InventoryTransaction(
            Long id,
            product product,
            TransactionType type,
            int quantity,
            LocalDateTime createdAt) {

        this.id = id;
        this.product = product;
        this.type = type;
        this.quantity = quantity;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public product getProduct() {
        return product;
    }

    public void setProduct(product product) {
        this.product = product;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
