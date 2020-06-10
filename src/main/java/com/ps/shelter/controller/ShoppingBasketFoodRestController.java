package com.ps.shelter.controller;

import com.ps.shelter.dto.ShoppingBasketFoodDTO;
import com.ps.shelter.service.ShoppingBasketFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/basket_food")
public class ShoppingBasketFoodRestController {

    private final ShoppingBasketFoodService shoppingBasketFoodService;

    @Autowired
    public ShoppingBasketFoodRestController(ShoppingBasketFoodService shoppingBasketFoodService) {
        this.shoppingBasketFoodService = shoppingBasketFoodService;
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    void delete(@RequestParam Long id){

        shoppingBasketFoodService.delete(id);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<ShoppingBasketFoodDTO> findAll(@RequestParam Long shoppingBasketId) {
        return shoppingBasketFoodService.findAllByShoppingBasketId(shoppingBasketId);
    }
}
