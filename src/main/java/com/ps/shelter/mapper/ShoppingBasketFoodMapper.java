package com.ps.shelter.mapper;

import com.ps.shelter.dto.ShoppingBasketFoodDTO;
import com.ps.shelter.entity.ShoppingBasketFood;

public interface ShoppingBasketFoodMapper {

    ShoppingBasketFood toEntity(ShoppingBasketFoodDTO shoppingBasketFoodDTO);

    ShoppingBasketFoodDTO toDTO(ShoppingBasketFood shoppingBasketFood);
}
