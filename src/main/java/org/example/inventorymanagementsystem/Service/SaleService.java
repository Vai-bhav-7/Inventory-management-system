package org.example.inventorymanagementsystem.Service;

import org.example.inventorymanagementsystem.dto.SaleRequest;
import org.example.inventorymanagementsystem.Exception.InsufficientStockException;
import org.example.inventorymanagementsystem.model.product;
import org.example.inventorymanagementsystem.model.Sale;
import org.example.inventorymanagementsystem.repository.productRepository;
import org.example.inventorymanagementsystem.repository.SaleRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.cache.annotation.CacheEvict;

import java.time.LocalDateTime;

@Service
public class SaleService {

    private final productRepository productRepository;
    private final SaleRepository saleRepository;

    public SaleService(
            productRepository productRepository,
            SaleRepository saleRepository) {

        this.productRepository = productRepository;
        this.saleRepository = saleRepository;
    }

    @CacheEvict(value = "products", key = "#request.productId")
    public Sale createSale(SaleRequest request) {

        product product = productRepository.findById(request.getProductId())
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        if (product.getQuantity() < request.getQuantity()) {
            throw new InsufficientStockException("Insufficient stock");
        }

        product.setQuantity(
                product.getQuantity() - request.getQuantity()
        );

        productRepository.save(product);

        Sale sale = new Sale();

        sale.setProduct(product);
        sale.setQuantity(request.getQuantity());
        sale.setSellingPrice(request.getSellingPrice());
        sale.setCustomerName(request.getCustomerName());
        sale.setCreatedAt(LocalDateTime.now());

        return saleRepository.save(sale);
    }

    public List<Sale> getSales() {
        return saleRepository.findAll();
    }
}