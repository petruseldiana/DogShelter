package com.ps.shelter.service.impl;

import com.ps.shelter.dto.ShoppingBasketFoodDTO;
import com.ps.shelter.entity.ShoppingBasketFood;
import com.ps.shelter.mapper.ShoppingBasketFoodMapper;
import com.ps.shelter.repository.ShoppingBasketFoodRepository;
import com.ps.shelter.service.ShoppingBasketFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingBasketFoodServiceImpl implements ShoppingBasketFoodService {

    private final ShoppingBasketFoodRepository shoppingBasketFoodRepository;
    private final ShoppingBasketFoodMapper shoppingBasketFoodMapper;

    @Autowired
    public ShoppingBasketFoodServiceImpl(ShoppingBasketFoodRepository shoppingBasketFoodRepository, ShoppingBasketFoodMapper shoppingBasketFoodMapper) {
        this.shoppingBasketFoodRepository = shoppingBasketFoodRepository;
        this.shoppingBasketFoodMapper = shoppingBasketFoodMapper;
    }

    @Override
    public void delete(Long id) {

        final ShoppingBasketFood shoppingBasketFood = shoppingBasketFoodRepository.findById(id)
                .orElseThrow(() -> { throw new EntityNotFoundException("Cannot find food in basket with ID: " + id); } );

        shoppingBasketFoodRepository.delete(shoppingBasketFood);
    }

    @Override
    public List<ShoppingBasketFoodDTO> findAllByShoppingBasketId(Long shoppingBasketId) {
        return shoppingBasketFoodRepository.findAll()
                .stream()
                .map(shoppingBasketFoodMapper::toDTO)
                .filter(shoppingBasketFoodDTO -> shoppingBasketFoodDTO.getShoppingBasketId().equals(shoppingBasketId))
                .collect(Collectors.toList());
    }

}
