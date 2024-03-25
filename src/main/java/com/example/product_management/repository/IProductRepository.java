package com.example.product_management.repository;

import com.example.product_management.model.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.function.Predicate;


public interface IProductRepository extends JpaRepository<Product,Long>  {
    Page<Product> findAll(Pageable pageable);
    @Query("SELECT p FROM Product p WHERE (?1 IS NULL OR p.price = ?1 ) AND (?2 IS NULL OR p.name = ?2) ")
    List<Product> searchAll(double price, String name);
}
