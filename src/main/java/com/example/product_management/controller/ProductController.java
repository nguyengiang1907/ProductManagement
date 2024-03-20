package com.example.product_management.controller;

import com.example.product_management.model.Product;
import com.example.product_management.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService iProductService;

    @GetMapping()
    public ModelAndView listProduct(@PageableDefault(10) Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("/product/index");
        Page<Product> products = iProductService.findAll(pageable);
        modelAndView.addObject("products",products);
        return modelAndView;
    }


    @GetMapping("/create")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product",new Product());
        return modelAndView;
    }
    @PostMapping("/create")
    public ModelAndView save(@ModelAttribute Product product){
        ModelAndView modelAndView = new ModelAndView("redirect:/products");
        iProductService.save(product);
        modelAndView.addObject("product",new Product());
        modelAndView.addObject("message","New customer created successfully");
        return modelAndView;
    }
    @GetMapping("/update/{id}")
    public ModelAndView showFormEdit(@PathVariable long id){
        Optional<Product> product = iProductService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/product/edit");
        modelAndView.addObject("product",product.get());
        return modelAndView;
    }
    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("product") Product product){
        ModelAndView modelAndView = new ModelAndView("redirect:/products");
        iProductService.save(product);
        modelAndView.addObject("message" ,"Update successful ");
        return modelAndView;
    }
    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<Product> product = iProductService.findById(id);
        if (product.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/product/delete");
            modelAndView.addObject("product", product.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error_404");
        }
    }
    @PostMapping("/delete")
    public ModelAndView delete(@ModelAttribute Product customer){
        ModelAndView modelAndView = new ModelAndView("redirect:/products");
        iProductService.remove(customer.getId());
        return modelAndView;
    }

}
