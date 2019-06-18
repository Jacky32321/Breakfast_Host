package com.example.test01.Model;

public class OrderFood {
    private String description;
    private String productName;
    private String productId;
    private String price;
    private String quantity;

    public OrderFood(String description, String productName, String productId, String price, String quantity) {
        this.description = description;
        this.productName = productName;
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
    }

    public OrderFood() {

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
