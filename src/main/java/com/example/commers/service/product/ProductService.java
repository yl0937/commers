package com.example.commers.service.product;

import com.example.commers.domain.product.Product;
import com.example.commers.repository.product.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public void createProduct(Product product){
        Product newProduct = new Product();
        newProduct.setName(product.getName());
        newProduct.setCategory(product.getCategory());
        newProduct.setPrice(product.getPrice());
        newProduct.setManufacturer(product.getManufacturer());
        newProduct.setCreateAt(LocalDate.now());
        productRepository.save(newProduct);
    }

    public List<Product> getProduct(String name){
        return productRepository.findAllByNameContaining(name);
    }

    public Optional<Product> getDetail(Integer id){
        return productRepository.findById(id);
    }
}
