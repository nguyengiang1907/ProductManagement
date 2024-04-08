package com.example.product_management.controller;

import com.example.product_management.model.Cart;
import com.example.product_management.model.Product;
import com.example.product_management.service.ICartService;
import com.example.product_management.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductAPI {
    @Autowired
    private IProductService iProductService;


    @GetMapping()
    public ResponseEntity<Page<Product>> findAll(@PageableDefault(value = 3) Pageable pageable) {
        Page<Product> productPage = iProductService.findAll(pageable);
        if (productPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productPage, HttpStatus.OK);
    }


}
