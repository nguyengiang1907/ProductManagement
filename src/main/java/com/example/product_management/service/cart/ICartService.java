package com.example.product_management.service.cart;

import com.example.product_management.model.Cart;
import com.example.product_management.service.IGenerateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICartService extends IGenerateService<Cart> {
    Cart saveAPI(Cart cart);
    Page<Cart> findAll(Pageable pageable);

}
