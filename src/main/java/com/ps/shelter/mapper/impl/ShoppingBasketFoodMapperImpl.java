package com.ps.shelter.mapper.impl;

import com.ps.shelter.dto.ShoppingBasketFoodDTO;
import com.ps.shelter.entity.Food;
import com.ps.shelter.entity.ShoppingBasket;
import com.ps.shelter.entity.ShoppingBasketFood;
import com.ps.shelter.mapper.ShoppingBasketFoodMapper;
import com.ps.shelter.repository.FoodRepository;
import com.ps.shelter.repository.ShoppingBasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
public class ShoppingBasketFoodMapperImpl implements ShoppingBasketFoodMapper {

    private final FoodRepository foodRepository;
    private final ShoppingBasketRepository shoppingBasketRepository;

    @Autowired
    public ShoppingBasketFoodMapperImpl(FoodRepository foodRepository, ShoppingBasketRepository shoppingBasketRepository) {
        this.foodRepository = foodRepository;
        this.shoppingBasketRepository = shoppingBasketRepository;
    }

    @Override
    public ShoppingBasketFood toEntity(ShoppingBasketFoodDTO shoppingBasketFoodDTO) {

        if(shoppingBasketFoodDTO != null){

            ShoppingBasketFood shoppingBasketFood = new ShoppingBasketFood();

            final Food food = foodRepository.findById(shoppingBasketFoodDTO.getFoodId())
                    .orElseThrow(() -> { throw new EntityNotFoundException("Cannot find food with ID: " + shoppingBasketFoodDTO.getFoodId()); } );

            final ShoppingBasket shoppingBasket = shoppingBasketRepository.findById(shoppingBasketFoodDTO.getShoppingBasketId())
                    .orElseThrow(() -> { throw new EntityNotFoundException("Cannot find basket with ID: " + shoppingBasketFoodDTO.getShoppingBasketId()); } );

            shoppingBasketFood.setId(shoppingBasketFoodDTO.getId());
            shoppingBasketFood.setFood(food);
            shoppingBasketFood.setQuantity(shoppingBasketFoodDTO.getQuantity());
            shoppingBasketFood.setShoppingBasket(shoppingBasket);

            return shoppingBasketFood;
        }

        return null;
    }

    @Override
    public ShoppingBasketFoodDTO toDTO(ShoppingBasketFood shoppingBasketFood) {

        if(shoppingBasketFood != null){

            ShoppingBasketFoodDTO shoppingBasketFoodDTO = new ShoppingBasketFoodDTO();

            shoppingBasketFoodDTO.setId(shoppingBasketFood.getId());
            shoppingBasketFoodDTO.setShoppingBasketId(shoppingBasketFood.getShoppingBasket().getId());
            shoppingBasketFoodDTO.setFoodId(shoppingBasketFood.getFood().getId());
            shoppingBasketFoodDTO.setQuantity(shoppingBasketFood.getQuantity());

            return shoppingBasketFoodDTO;
        }

        return null;
    }
}
