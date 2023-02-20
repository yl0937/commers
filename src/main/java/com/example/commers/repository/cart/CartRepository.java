package com.example.commers.repository.cart;

import com.example.commers.domain.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Integer> {

    List<Cart> findAllByUserId(Integer userId);
}
