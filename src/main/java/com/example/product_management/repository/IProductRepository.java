package com.example.product_management.repository;

import com.example.product_management.model.Product;
import com.example.product_management.specification.PaginateRequest;
import com.example.product_management.specification.ProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product,Long>, JpaSpecificationExecutor<Product> {
    Page<Product> findAll(Pageable pageable);
    @Query("select p from Product  p where p.id = ?1")
    Product findProductById(long id);
}
