package com.ps.shelter.service;

import com.ps.shelter.dto.ShoppingBasketFoodDTO;

import java.util.List;

public interface ShoppingBasketFoodService {

    void delete(Long id);

    List<ShoppingBasketFoodDTO> findAllByShoppingBasketId(Long id);
}
