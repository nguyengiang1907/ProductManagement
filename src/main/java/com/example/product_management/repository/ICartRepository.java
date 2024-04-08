package com.example.product_management.repository;

import com.example.product_management.model.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICartRepository extends JpaRepository<Cart,Long> {
    Page<Cart> findAll(Pageable pageable);
}
