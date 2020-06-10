package com.ps.shelter.service;

import com.ps.shelter.dto.DiscountDTO;
import com.ps.shelter.dto.ShoppingBasketDTO;
import com.ps.shelter.dto.ShoppingBasketFoodDTO;
import com.ps.shelter.entity.ShoppingBasket;

import java.util.List;
import java.util.Optional;

public interface ShoppingBasketService {

    DiscountDTO checkout(Long shoppingBasketId);

    ShoppingBasketDTO create(ShoppingBasketDTO shoppingBasketDTO);

    void addArticle(ShoppingBasketFoodDTO shoppingBasketFoodDTO);

    void delete(Long shoppingBasketId);

    Optional<ShoppingBasket> findById(Long id);

    ShoppingBasketDTO findByUserId(Long userId);

    List<ShoppingBasketDTO> getAll();
}
