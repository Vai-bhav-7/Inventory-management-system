package org.example.inventorymanagementsystem.repository;

import org.example.inventorymanagementsystem.model.product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface productRepository extends JpaRepository<product, Long> {
}