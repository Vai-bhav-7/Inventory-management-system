package org.example.inventorymanagementsystem.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private product product;

    private int quantity;

    private double sellingPrice;

    private String customerName;

    private LocalDateTime createdAt;

    public Sale() {
    }

    public Sale(Long id, product product, int quantity,
                double sellingPrice, String customerName,
                LocalDateTime createdAt) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.sellingPrice = sellingPrice;
        this.customerName = customerName;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}