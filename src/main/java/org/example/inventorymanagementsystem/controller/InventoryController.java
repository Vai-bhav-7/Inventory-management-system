package org.example.inventorymanagementsystem.controller;

import org.example.inventorymanagementsystem.Service.InventoryService;
import org.example.inventorymanagementsystem.model.InventoryTransaction;
import org.springframework.web.bind.annotation.*;
import org.example.inventorymanagementsystem.repository.InventoryTransactionRepository;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;
    private final InventoryTransactionRepository transactionRepository;

    public InventoryController(
            InventoryService inventoryService,
            InventoryTransactionRepository transactionRepository) {

        this.inventoryService = inventoryService;
        this.transactionRepository = transactionRepository;
    }

    @PostMapping("/stock-in/{productId}")
    public InventoryTransaction stockIn(
            @PathVariable Long productId,
            @RequestParam int quantity) {

        return inventoryService.stockIn(productId, quantity);
    }

    @PostMapping("/stock-out/{productId}")
    public InventoryTransaction stockOut(
            @PathVariable Long productId,
            @RequestParam int quantity) {

        return inventoryService.stockOut(productId, quantity);
    }

    @GetMapping("/transactions")
    public java.util.List<InventoryTransaction> getTransactions() {
        return transactionRepository.findAll();
    }
}
