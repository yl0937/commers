package com.example.commers.controller;

import com.example.commers.domain.Category;
import com.example.commers.domain.Product;
import com.example.commers.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;


    @PreAuthorize("hasRole('MANAGE')")
    @PostMapping("/register")
    void register(@RequestBody Product product){
        productService.createProduct(product);
    }

    @GetMapping("/search")
    List<Product> searchProduct(@RequestBody String name){
        return productService.getProduct(name);
    }

    @GetMapping("/detail")
    Optional<Product> searchDetail(@RequestParam Integer id){
        return productService.getDetail(id);
    }

    @PreAuthorize("hasRole('MANAGE')")
    @DeleteMapping("/delete")
    void removeProduct(@RequestParam Integer id){
        productService.removeProduct(id);
    }

    @PreAuthorize("hasRole('MANAGE')")
    @PostMapping("/category")
    void addCategory(@RequestBody Category category){
        productService.addCategory(category);
    }
}
