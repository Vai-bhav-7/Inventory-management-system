package org.example.inventorymanagementsystem.controller;

import jakarta.validation.Valid;
import org.example.inventorymanagementsystem.Service.SaleService;
import org.example.inventorymanagementsystem.dto.SaleRequest;
import org.example.inventorymanagementsystem.model.Sale;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/sales")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping
    public Sale createSale(
            @Valid @RequestBody SaleRequest request) {

        return saleService.createSale(request);
    }

    @GetMapping
    public List<Sale> getSales() {
        return saleService.getSales();
    }
}