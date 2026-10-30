package org.example.inventorymanagementsystem.Service;

import org.example.inventorymanagementsystem.dto.PurchaseRequest;
import org.example.inventorymanagementsystem.model.product;
import org.example.inventorymanagementsystem.model.Purchase;
import org.example.inventorymanagementsystem.repository.productRepository;
import org.example.inventorymanagementsystem.repository.PurchaseRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.cache.annotation.CacheEvict;

import java.time.LocalDateTime;

@Service
public class PurchaseService {
    private final productRepository productRepository;
    private final PurchaseRepository purchaseRepository;

    public PurchaseService(
            productRepository productRepository,
            PurchaseRepository purchaseRepository) {

        this.productRepository = productRepository;
        this.purchaseRepository = purchaseRepository;
    }

    @CacheEvict(value = "products", key = "#request.productId")
    public Purchase createPurchase(PurchaseRequest request) {

        product product = productRepository.findById(request.getProductId())
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        product.setQuantity(
                product.getQuantity() + request.getQuantity()
        );

        productRepository.save(product);

        Purchase purchase = new Purchase();

        purchase.setProduct(product);
        purchase.setQuantity(request.getQuantity());
        purchase.setPurchasePrice(request.getPurchasePrice());
        purchase.setSupplierName(request.getSupplierName());
        purchase.setCreatedAt(LocalDateTime.now());

        return purchaseRepository.save(purchase);
    }

    public List<Purchase> getPurchases() {
        return purchaseRepository.findAll();
    }
}
