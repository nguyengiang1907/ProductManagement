
package com.example.product_management.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name ="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private double price;
    private int quantity;
    private String describes;
    private String image;
    @ManyToOne
    @JoinColumn(name = "users_id")
    private User idUser;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category idCategory;
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public Category getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Category idCategory) {
        this.idCategory = idCategory;
    }

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

    public String getDescribe() {
        return describes;
    }

    public void setDescribe(String describe) {
        this.describes = describe;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Product() {
    }

    public Product(long id, String name, double price, int quantity, String describes, String image, Category idCategory, User idUser) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.describes = describes;
        this.image = image;
        this.idCategory = idCategory;
        this.idUser = idUser;
    }

    public Product(String name, double price, int quantity, String describes, String image, Category idCategory, User idUser) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.describes = describes;
        this.image = image;
        this.idCategory = idCategory;
        this.idUser = idUser;
    }
}
