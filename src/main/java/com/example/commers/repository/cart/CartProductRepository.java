package com.example.commers.repository.cart;

import com.example.commers.domain.cart.CartProduct;
import com.example.commers.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

public interface CartProductRepository extends JpaRepository<CartProduct,Integer> {

    List<CartProduct> findAllByCartIdIs(Integer cartId);

}
