package org.example.inventorymanagementsystem.controller;

import jakarta.validation.Valid;
import org.example.inventorymanagementsystem.Service.PurchaseService;
import org.example.inventorymanagementsystem.dto.PurchaseRequest;
import org.example.inventorymanagementsystem.model.Purchase;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {
    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping
    public Purchase createPurchase(
            @Valid @RequestBody PurchaseRequest request) {

        return purchaseService.createPurchase(request);
    }

    @GetMapping
    public List<Purchase> getPurchases() {
        return purchaseService.getPurchases();
    }
}
