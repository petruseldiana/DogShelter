package com.ps.shelter.repository;

import com.ps.shelter.dto.ShoppingBasketFoodDTO;
import com.ps.shelter.entity.ShoppingBasketFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingBasketFoodRepository extends JpaRepository<ShoppingBasketFood, Long> {

//    List<ShoppingBasketFoodDTO> findAllByShoppingBasketId(Long shoppingBasketId);

    void deleteShoppingBasketFoodByFoodId(Long id);
}
