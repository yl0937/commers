package com.example.commers.service.product;

import com.example.commers.domain.product.Category;
import com.example.commers.domain.product.Product;
import com.example.commers.repository.product.CategoryRepository;
import com.example.commers.repository.product.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    public void createProduct(Product product){
        Product newProduct = new Product();
        newProduct.setName(product.getName());
        newProduct.setCategory(product.getCategory());
        newProduct.setPrice(product.getPrice());
        newProduct.setManufacturer(product.getManufacturer());
        newProduct.setCreateAt(LocalDate.now());
        newProduct.setImgUrl(product.getImgUrl());
        productRepository.save(newProduct);
    }

    public List<Product> getProduct(String name){
        return productRepository.findAllByNameContaining(name);
    }

    public Optional<Product> getDetail(Integer id){
        return productRepository.findById(id);
    }

    public void removeProduct(Integer id) {
        productRepository.deleteById(id);
    }

    public void addCategory(Category category) {
        Category newCategory = new Category();
        newCategory.setName(category.getName());
        categoryRepository.save(newCategory);
    }
}
