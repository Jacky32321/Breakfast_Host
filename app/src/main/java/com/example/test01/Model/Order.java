package com.example.test01.Model;

public class Order {

    private String productId;
    private String productName;
    private String quantity;
    private String price;
    private String description;

    public Order() {
    }

    public Order(String foodId, String foodName, String quantity, String price, String description) {
        this.productId = foodId;
        this.productName = foodName;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

