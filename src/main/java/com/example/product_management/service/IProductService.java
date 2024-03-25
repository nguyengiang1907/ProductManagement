package com.example.product_management.service;

import com.example.product_management.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface IProductService extends IGenerateService<Product> {
    Page<Product> findAll(Pageable pageable);
    Iterable<Product> searchAll(double price, String name);
}
