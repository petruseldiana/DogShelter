package com.ps.shelter.mapper;

import com.ps.shelter.dto.ShoppingBasketDTO;
import com.ps.shelter.entity.ShoppingBasket;

public interface ShoppingBasketMapper {

    ShoppingBasket toEntity(ShoppingBasketDTO shoppingBasketDTO);

    ShoppingBasketDTO toDTO(ShoppingBasket shoppingBasket);
}
