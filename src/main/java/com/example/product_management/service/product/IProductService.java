package com.example.product_management.service.product;

import com.example.product_management.model.Product;
import com.example.product_management.service.IGenerateService;
import com.example.product_management.specification.PaginateRequest;
import com.example.product_management.specification.ProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IProductService extends IGenerateService<Product> {
    Page<Product> findAll(Pageable pageable);
    Page<Product> search(PaginateRequest paginateRequest, ProductRequest productRequest);
    Product findProductById(long id);

}
