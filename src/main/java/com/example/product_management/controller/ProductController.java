package com.example.product_management.controller;

import com.example.product_management.model.Category;
import com.example.product_management.model.Product;
import com.example.product_management.model.ProductForm;
import com.example.product_management.service.ICategoryService;
import com.example.product_management.service.IProductService;
import com.example.product_management.specification.PaginateRequest;
import com.example.product_management.specification.ProductRequest;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService iProductService;
    @Autowired
    private ICategoryService iCategoryService;
    @Value("${folder_upload}")
    private String fileUpload;

    @ModelAttribute("category")
    public Iterable<Category> findALl(){
        return iCategoryService.findAll();
    }
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
    public ModelAndView save(@ModelAttribute ProductForm productForm, RedirectAttributes redirect) throws IOException {
        ModelAndView modelAndView = new ModelAndView("redirect:/products");
        MultipartFile multipartFile = productForm.getImage();
        String nameFile = multipartFile.getOriginalFilename();
        Optional<Category> category = iCategoryService.findById(productForm.getIdCategory());
        FileCopyUtils.copy(productForm.getImage().getBytes(),new File(fileUpload+nameFile));
        Product product = new Product(productForm.getId(), productForm.getName(), productForm.getPrice(), productForm.getQuantity(), productForm.getDescribes(), nameFile,category.get());
        iProductService.save(product);
        redirect.addFlashAttribute("satus","error");
        return modelAndView;
    }
    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("product") Product product, RedirectAttributes redirect){
        ModelAndView modelAndView = new ModelAndView("redirect:/products");
        Optional<Product> products = iProductService.findById(product.getId());
        product.setImage(products.get().getImage());
        iProductService.save(product);
        redirect.addFlashAttribute("msg" ,"Update successful ");
        return modelAndView;
    }
    @GetMapping("/delete/{id}")
    public ModelAndView delete(@ModelAttribute Product product){
        ModelAndView modelAndView = new ModelAndView("redirect:/products");
        iProductService.remove(product.getId());
        modelAndView.addObject("msg" ,"Delete successful ");
        return modelAndView;
    }
    @GetMapping("/search")
    public ModelAndView search(@RequestParam(value = "name", defaultValue = "null") String name,
                               @RequestParam(value = "price", required = false, defaultValue = "0") double price,
                               @RequestParam(value = "quantity", required = false, defaultValue = "0") int quantity,
                               @RequestParam(value = "describes", defaultValue = "null") String describes,
        if (iProductService.findById(product.getId()).isPresent()){
            iProductService.remove(product.getId());
        }
        return modelAndView;
    }@GetMapping("/search")
    public ModelAndView search(@RequestParam(value = "name" , required = false) String name,
                               @RequestParam(value = "price", defaultValue = "0") double price,
                               @RequestParam(value = "quantity", defaultValue = "0") int quantity,
                               @RequestParam(value = "describes", required = false) String describes,
                               @RequestParam(name = "page", required = false, defaultValue = "0") int page,
                               @RequestParam(name = "size", required = false, defaultValue = "10") int size) {

        ModelAndView modelAndView = new ModelAndView("/product/index");
        Page<Product> pages = iProductService.search(new PaginateRequest(page, size), new ProductRequest(name, price, quantity, describes));
        modelAndView.addObject("products", pages);
        if (pages.isEmpty()){
            modelAndView.addObject("message","khong hop le");
        }
        return modelAndView;
    }
}
