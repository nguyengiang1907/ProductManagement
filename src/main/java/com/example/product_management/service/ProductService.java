package com.example.product_management.service;

import com.example.product_management.model.Product;
import com.example.product_management.repository.IProductRepository;
import com.example.product_management.specification.PaginateRequest;
import com.example.product_management.specification.ProductRequest;
import com.example.product_management.specification.ProductSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository iProductRepository;

    @Override
    public Iterable<Product> findAll() {
        return iProductRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return iProductRepository.findById(id);
    }

    @Override
    public void save(Product product) {
        iProductRepository.save(product);
    }

    @Override
    public void remove(Long id) {
        iProductRepository.deleteById(id);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return iProductRepository.findAll(pageable);
    }
    @Override
    public Page<Product> search(PaginateRequest paginateRequest, ProductRequest productRequest) {
        return iProductRepository.findAll(new ProductSpec(productRequest), PageRequest.of(paginateRequest.getPage(),paginateRequest.getSize()));
    }

    @Override
    public Product findProductById(long id) {
        return iProductRepository.findProductById(id);
    }
}
