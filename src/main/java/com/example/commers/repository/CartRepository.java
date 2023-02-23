package com.example.commers.repository;

import com.example.commers.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Integer> {

    List<Cart> findAllByUserId(Integer userId);


}
