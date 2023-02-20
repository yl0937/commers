package com.example.commers.service.cart;


import com.example.commers.domain.cart.Cart;
import com.example.commers.domain.cart.CartProduct;
import com.example.commers.domain.product.Product;
import com.example.commers.repository.cart.CartProductRepository;
import com.example.commers.repository.cart.CartRepository;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final CartProductRepository cartProductRepository;

    public CartService(CartRepository cartRepository,
                       CartProductRepository cartProductRepository) {
        this.cartRepository = cartRepository;
        this.cartProductRepository = cartProductRepository;
    }

    public void addCart(Cart cart) {
        Cart newCart = new Cart();
        newCart.setUserId(cart.getUserId());
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

    public void deleteProductToCart(Integer id) {
        cartProductRepository.deleteById(id);
    }

    public List<CartProduct> getProductListToCart(Integer cartId) {
        List<Product> productes;
        List<CartProduct> cartList = cartProductRepository.findAllByCartIdIs(cartId);
        for(int i=0;i<cartList.size();i++)
        {

        }
        return cartProductRepository.findAllByCartIdIs(cartId);
    }
}
