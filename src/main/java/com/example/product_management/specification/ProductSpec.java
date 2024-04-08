package com.example.product_management.specification;

import com.example.product_management.model.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;


public class ProductSpec implements Specification<Product> {
    private ProductRequest productRequest;

    public ProductSpec(ProductRequest productRequest) {
        this.productRequest = productRequest;
    }

    @Override
    public Predicate toPredicate(Root<Product> root,
                                 CriteriaQuery<?> query,
                                 CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();
        if (!productRequest.getName().isEmpty()) {
            predicates.add(criteriaBuilder.like(root.get("name"), "%" + productRequest.getName() + "%"));
        }
        if (productRequest.getPrice() != 0) {
            predicates.add(criteriaBuilder.lessThan(root.get("price"), productRequest.getPrice()));
        }
        if (productRequest.getQuantity() != 0) {
            predicates.add(criteriaBuilder.lessThan(root.get("quantity"), productRequest.getQuantity()));
        }
        if (!productRequest.getDescribes().isEmpty()) {
            predicates.add(criteriaBuilder.like(root.get("describes"), "%" + productRequest.getDescribes() + "%"));
        }
        if(!predicates.isEmpty()){
            query.where(predicates.toArray(new Predicate[0]));
        }
        return query.getRestriction();
    }
}