package org.example.inventorymanagementsystem.repository;

import org.example.inventorymanagementsystem.model.InventoryTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryTransactionRepository
        extends JpaRepository<InventoryTransaction, Long> {
}
