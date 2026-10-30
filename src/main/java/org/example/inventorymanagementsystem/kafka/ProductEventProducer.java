package org.example.inventorymanagementsystem.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductEventProducer {

    private final KafkaTemplate<String, ProductEvent> kafkaTemplate;

    public ProductEventProducer(
            KafkaTemplate<String, ProductEvent> kafkaTemplate) {

        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendProductCreatedEvent(ProductEvent event) {

        kafkaTemplate.send("product-events", event);
    }
}