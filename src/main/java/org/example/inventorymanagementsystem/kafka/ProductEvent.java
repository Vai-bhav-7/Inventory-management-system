package org.example.inventorymanagementsystem.kafka;

public class ProductEvent {

    private String eventType;
    private Long productId;
    private String name;
    private int quantity;

    public ProductEvent() {
    }

    public ProductEvent(
            String eventType,
            Long productId,
            String name,
            int quantity) {

        this.eventType = eventType;
        this.productId = productId;
        this.name = name;
        this.quantity = quantity;
    }

    public String getEventType() {
        return eventType;
    }

    public Long getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }
}