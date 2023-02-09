package com.example.commers.controller.product;

import com.example.commers.domain.product.Product;
import com.example.commers.service.product.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

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
}
