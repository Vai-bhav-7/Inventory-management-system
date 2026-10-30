package org.example.inventorymanagementsystem.repository;

import org.example.inventorymanagementsystem.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}