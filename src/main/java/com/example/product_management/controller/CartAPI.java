package com.example.product_management.controller;

import com.example.product_management.model.Cart;
import com.example.product_management.service.cart.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
@RequestMapping("/api/carts")
public class CartAPI {
    @Autowired
    private ICartService iCartService;
    @GetMapping()
    public ResponseEntity<Page<Cart>> findAll(@PageableDefault(value = 10) Pageable pageable){
        Page<Cart> carts = iCartService.findAll(pageable);
        if (carts.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(carts,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Cart>> findById(@PathVariable long id) {
        Optional<Cart> cart = iCartService.findById(id);
        if (cart.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Cart> save(@RequestBody Cart cart) {
        return new ResponseEntity<>(iCartService.saveAPI(cart), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Cart> update(@PathVariable long id,@RequestBody Cart cart){
        Optional<Cart> optionalCart = iCartService.findById(id);
        if (!optionalCart.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        cart.setId(optionalCart.get().getId());
        return new ResponseEntity<>(iCartService.saveAPI(cart),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Cart> delete(@PathVariable long id){
        Optional<Cart> optionalCart = iCartService.findById(id);
        if (optionalCart.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        iCartService.remove(id);
        return new ResponseEntity<>(optionalCart.get(),HttpStatus.OK);
    }
}
