package com.example.product_management.specification;

import com.example.product_management.model.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;


public class ProductSpec implements Specification<Product> {
    private ProductRequest productRequest;

    public ProductSpec(ProductRequest productRequest) {
        this.productRequest = productRequest;
    }

    @Override
    public Predicate toPredicate(Root<Product> root,
                                 CriteriaQuery<?> query,
                                 CriteriaBuilder criteriaBuilder) {

        if (productRequest.getName() != null) {
            query.where(criteriaBuilder.like(root.get("name"), "%" + productRequest.getName() + "%"));
        }
        if (productRequest.getPrice() != 0) {
            query.where(criteriaBuilder.lessThan(root.get("price"), productRequest.getPrice()));
        }
        if (productRequest.getQuantity() != 0) {
            query.where(criteriaBuilder.lessThan(root.get("quantity"), productRequest.getQuantity()));
        }
        if (productRequest.getDescribes() != null) {
            query.where(criteriaBuilder.like(root.get("describes"), "%" + productRequest.getDescribes() + "%"));
        }
        return query.getRestriction();
    }
}