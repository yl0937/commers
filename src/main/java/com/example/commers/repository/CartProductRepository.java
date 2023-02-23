package com.example.commers.repository;

import com.example.commers.domain.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartProductRepository extends JpaRepository<CartProduct,Integer> {

    List<CartProduct> findAllByCartIdIs(Integer cartId);

    Optional<CartProduct> findByCartIdAndProductId(Integer cartId, Integer productId);
}
