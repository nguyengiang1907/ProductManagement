package com.example.product_management.specification;

public class ProductRequest {
    private String name;
    private double price;
    private int quantity;
    private String describes;

    public String getName() {
        return name;
    }

    public ProductRequest(String name, double price, int quantity, String describes) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.describes = describes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }
}
