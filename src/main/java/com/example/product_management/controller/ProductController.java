package com.example.product_management.controller;

import com.example.product_management.PaginateRequest;
import com.example.product_management.ProductRequest;
import com.example.product_management.model.Product;
import com.example.product_management.model.ProductForm;
import com.example.product_management.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService iProductService;
    @Value("${folder_upload}")
    private String fileUpload;

    @GetMapping()
    public ModelAndView listProduct(@PageableDefault(10) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("/product/index");
        Page<Product> products = iProductService.findAll(pageable);
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("productForm", new ProductForm());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView save(@ModelAttribute ProductForm productForm) throws IOException {
        ModelAndView modelAndView = new ModelAndView("redirect:/products");
        MultipartFile multipartFile = productForm.getImage();
        String nameFile = multipartFile.getOriginalFilename();
        FileCopyUtils.copy(productForm.getImage().getBytes(), new File(fileUpload + nameFile));
        Product product = new Product(productForm.getId(), productForm.getName(), productForm.getPrice(), productForm.getQuantity(), productForm.getDescribes(), nameFile);
        iProductService.save(product);
        modelAndView.addObject("message", "New customer created successfully");
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("product") Product product) {
        ModelAndView modelAndView = new ModelAndView("redirect:/products");
        iProductService.save(product);
        modelAndView.addObject("message", "Update successful ");
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
    public ModelAndView delete(@ModelAttribute Product customer) {
        ModelAndView modelAndView = new ModelAndView("redirect:/products");
        iProductService.remove(customer.getId());
        return modelAndView;
    }

    @GetMapping("/search")
    public ModelAndView search(@RequestParam(value = "name", defaultValue = "null") String name,
                               @RequestParam(value = "price" ,defaultValue = "0") double price,
                               @RequestParam(value = "quantity",defaultValue = "0") int quantity,
                               @RequestParam(value = "describes",defaultValue = "null") String describes,
                               @RequestParam(name = "page", required = false, defaultValue = "0") int page,
                               @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        ModelAndView modelAndView = new ModelAndView("/product/index");
        Page<Product> pages = iProductService.search(new PaginateRequest(page, size), new ProductRequest(name, price, quantity, describes));
        modelAndView.addObject("products", pages);
        return modelAndView;
    }
}

