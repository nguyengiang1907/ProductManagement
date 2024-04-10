package com.example.product_management.service;

import com.example.product_management.model.Cart;
import com.example.product_management.model.Product;
import com.example.product_management.repository.ICartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CartService implements ICartService {
    @Autowired
    private ICartRepository iCartRepository;
    @Override
    public Iterable<Cart> findAll() {
        return iCartRepository.findAll();
    }

    @Override
    public Optional<Cart> findById(Long id) {
        return iCartRepository.findById(id);
    }

    @Override
    public void save(Cart cart) {
        iCartRepository.save(cart);
    }

    @Override
    public void remove(Long id) {
        iCartRepository.deleteById(id);
    }

    @Override
    public Cart saveAPI(Cart cart) {
        return iCartRepository.save(cart);
    }

    @Override
    public Page<Cart> findAll(Pageable pageable) {
        return iCartRepository.findAll(pageable);
    }
}
