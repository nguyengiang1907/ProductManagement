package com.example.product_management.model;

import org.springframework.web.multipart.MultipartFile;

public class ProductForm {
    private long id;
    private String name;
    private double price;
    private int quantity;
    private String describes;
    private MultipartFile image;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
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

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public ProductForm(long id, String name, double price, int quantity, String describes, MultipartFile image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.describes = describes;
        this.image = image;
    }

    public ProductForm() {
    }
}