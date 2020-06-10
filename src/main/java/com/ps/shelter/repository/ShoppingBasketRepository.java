package com.ps.shelter.repository;

import com.ps.shelter.entity.ShoppingBasket;
import com.ps.shelter.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingBasketRepository extends JpaRepository<ShoppingBasket, Long> {

    ShoppingBasket findByUser(User user);
}
