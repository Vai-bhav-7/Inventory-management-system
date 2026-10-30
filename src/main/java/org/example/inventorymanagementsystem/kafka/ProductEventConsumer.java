package org.example.inventorymanagementsystem.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ProductEventConsumer {

    @KafkaListener(
            topics = "product-events",
            groupId = "inventory-group"
    )
    public void consumeProductEvent(ProductEvent event) {

        System.out.println(
                "Received Event: " +
                        event.getEventType() +
                        " | Product ID: " +
                        event.getProductId() +
                        " | Name: " +
                        event.getName() +
                        " | Quantity: " +
                        event.getQuantity()
        );
    }
}