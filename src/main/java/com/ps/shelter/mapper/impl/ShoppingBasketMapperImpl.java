package com.ps.shelter.mapper.impl;

import com.ps.shelter.dto.ShoppingBasketDTO;
import com.ps.shelter.entity.ShoppingBasket;
import com.ps.shelter.mapper.ShoppingBasketMapper;
import org.springframework.stereotype.Component;

@Component
public class ShoppingBasketMapperImpl implements ShoppingBasketMapper {


    @Override
    public ShoppingBasket toEntity(ShoppingBasketDTO shoppingBasketDTO) {

        if(shoppingBasketDTO != null){

            ShoppingBasket shoppingBasket = new ShoppingBasket();

            shoppingBasket.setId(shoppingBasketDTO.getId());
            shoppingBasket.getUser().setId(shoppingBasketDTO.getUser_id());

            return shoppingBasket;
        }

        return null;
    }

    @Override
    public ShoppingBasketDTO toDTO(ShoppingBasket shoppingBasket) {

        if(shoppingBasket != null){

            ShoppingBasketDTO shoppingBasketDTO = new ShoppingBasketDTO();

            shoppingBasketDTO.setId(shoppingBasket.getId());
            shoppingBasketDTO.setUser_id(shoppingBasket.getUser().getId());

            return shoppingBasketDTO;
        }

        return null;
    }
}
