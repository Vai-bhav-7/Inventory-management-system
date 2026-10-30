package org.example.inventorymanagementsystem.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private product product;

    private int quantity;

    private double purchasePrice;

    private String supplierName;

    private LocalDateTime createdAt;

    public Purchase() {
    }

    public Purchase(Long id, product product, int quantity,
                    double purchasePrice, String supplierName,
                    LocalDateTime createdAt) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
        this.supplierName = supplierName;
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

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
