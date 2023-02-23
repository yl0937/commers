package com.example.commers.service;


import com.example.commers.domain.Cart;
import com.example.commers.domain.CartProduct;
import com.example.commers.domain.Product;
import com.example.commers.repository.CartProductRepository;
import com.example.commers.repository.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartProductRepository cartProductRepository;

    private final ProductService productService;


    public void addCart(Integer userId) {
        Cart newCart = new Cart();
        newCart.setUserId(userId);
        newCart.setCreateAt(LocalDate.now());
        cartRepository.save(newCart);
    }

    public void deleteCart(Integer id) {
    cartRepository.deleteById(id);
    }

    public List<Cart> searchCart(Integer userId) {
        return cartRepository.findAllByUserId(userId);
    }

    public void addProductToCart(CartProduct cartProduct) {
        CartProduct newCartProduct = new CartProduct();
        newCartProduct.setProductId(cartProduct.getProductId());
        newCartProduct.setCartId(cartProduct.getCartId());
        cartProductRepository.save(newCartProduct);
    }

    public void deleteProductToCart(Integer cartId,Integer productId) {
        Optional<CartProduct> cartProduct = cartProductRepository.findByCartIdAndProductId(cartId,productId);
        cartProductRepository.deleteById(cartProduct.get().getId());
    }

    public List<Optional<Product>> getProductListToCart(Integer cartId) {
        List<CartProduct> cartList = cartProductRepository.findAllByCartIdIs(cartId);
        List<Optional<Product>> resultList = new ArrayList<>();
        for (CartProduct cartProduct : cartList) {
            resultList.add(productService.getDetail(cartProduct.getProductId()));
        }
        return resultList;
    }

    public Optional<Cart> searchCartbyId(Integer cartId){
        return cartRepository.findById(cartId);
    }
}
