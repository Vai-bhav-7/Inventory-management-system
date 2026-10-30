package org.example.inventorymanagementsystem.repository;

import org.example.inventorymanagementsystem.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
