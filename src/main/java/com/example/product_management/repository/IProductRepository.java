package com.example.product_management.repository;

import com.example.product_management.model.Product;
import com.example.product_management.specification.PaginateRequest;
import com.example.product_management.specification.ProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface IProductRepository extends JpaRepository<Product,Long>, JpaSpecificationExecutor<Product> {
    Page<Product> findAll(Pageable pageable);

}
