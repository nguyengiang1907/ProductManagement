package com.example.product_management.repository;

import com.example.product_management.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IProductRepository extends JpaRepository<Product,Long> {
    Page<Product> findAll(Pageable pageable);
}
