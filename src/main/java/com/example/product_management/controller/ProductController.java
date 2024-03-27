package com.example.product_management.controller;

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
    public ModelAndView listProduct(@PageableDefault(10) Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("/product/index");
        Page<Product> products = iProductService.findAll(pageable);
        modelAndView.addObject("products",products);
        modelAndView.addObject("productForm",new ProductForm());
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView showFromCreate(){
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("productForm",new ProductForm());
        return modelAndView;
    }
    @GetMapping("/update/{id}")
    public ModelAndView showFromUpdate(@PathVariable Long id){
        Optional<Product> product = iProductService.findById(id);
        if (product.isPresent()){
            ModelAndView modelAndView = new ModelAndView("/product/update");
            modelAndView.addObject("productForm",product.get());
            return modelAndView;
        }
            return new ModelAndView("error_404");
    }
    @PostMapping("/create")
    public ModelAndView save(@ModelAttribute ProductForm productForm) throws IOException {
        ModelAndView modelAndView = new ModelAndView("redirect:/products");
        MultipartFile multipartFile = productForm.getImage();
        String nameFile = multipartFile.getOriginalFilename();
        FileCopyUtils.copy(productForm.getImage().getBytes(),new File(fileUpload+nameFile));
        Product product = new Product(productForm.getId(), productForm.getName(), productForm.getPrice(), productForm.getQuantity(), productForm.getDescribes(), nameFile);
        iProductService.save(product);
        modelAndView.addObject("message","New customer created successfully");
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
    public ModelAndView delete(@ModelAttribute Product product){
        ModelAndView modelAndView = new ModelAndView("redirect:/products");
        iProductService.remove(product.getId());
        return modelAndView;
    }

}
