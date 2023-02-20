package com.example.commers.controller;

import com.example.commers.domain.cart.Cart;
import com.example.commers.domain.cart.CartProduct;
import com.example.commers.domain.product.Product;
import com.example.commers.service.cart.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    void addCart(@RequestBody Cart cart){
        cartService.addCart(cart);
    }
    @DeleteMapping("/delete")
    void addCart(@RequestParam Integer id){
        cartService.deleteCart(id);
    }

    @GetMapping("/search")
    List<Cart> searchCart(@RequestParam Integer userId){
        return cartService.searchCart(userId);
    }

    @PostMapping("/product")
    void addProductToCart(@RequestBody CartProduct cartProduct){
        cartService.addProductToCart(cartProduct);
    }

    @DeleteMapping("/product/delete")
    void addProductToCart(@RequestParam Integer id){
        cartService.deleteProductToCart(id);
    }

    @GetMapping("/productList")
    List<CartProduct> getProductListToCart(@RequestParam Integer cartId){
        return cartService.getProductListToCart(cartId);
    }


}
