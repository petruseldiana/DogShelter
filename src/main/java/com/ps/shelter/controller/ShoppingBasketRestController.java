package com.ps.shelter.controller;

import com.ps.shelter.dto.ShoppingBasketDTO;
import com.ps.shelter.dto.ShoppingBasketFoodDTO;
import com.ps.shelter.service.ShoppingBasketService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@Api
@RequestMapping(value = "/basket")
public class ShoppingBasketRestController {

    private final ShoppingBasketService shoppingBasketService;

    @Autowired
    public ShoppingBasketRestController(ShoppingBasketService shoppingBasketService) {
        this.shoppingBasketService = shoppingBasketService;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void save(@RequestBody ShoppingBasketDTO shoppingBasketDTO){

        shoppingBasketService.create(shoppingBasketDTO);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addToCart(@RequestBody ShoppingBasketFoodDTO shoppingBasketFoodDTO){

        if(shoppingBasketFoodDTO.getQuantity() != 0){

            shoppingBasketService.addArticle(shoppingBasketFoodDTO);
        }else{

            throw new EntityNotFoundException("You must introduce quantity!");
        }
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<ShoppingBasketDTO> list() {
        return shoppingBasketService.getAll();
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ShoppingBasketDTO findByUserId(@RequestParam Long userId) {
        return shoppingBasketService.findByUserId(userId);
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.GET)
    public void checkout(@RequestParam Long shoppingBasketId){

        shoppingBasketService.checkout(shoppingBasketId);
    }
}
