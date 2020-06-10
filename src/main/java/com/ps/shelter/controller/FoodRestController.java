package com.ps.shelter.controller;

import com.ps.shelter.dto.FoodDTO;
import com.ps.shelter.service.FoodService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api
@RequestMapping(value = "/food")
public class FoodRestController {

    private final FoodService foodService;

    @Autowired
    public FoodRestController(FoodService foodService) {
        this.foodService = foodService;
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public void save(@RequestBody @Valid FoodDTO foodDTO, @RequestParam String supplierName, @RequestParam String supplierEmail) {

        foodService.save(foodDTO, supplierName, supplierEmail);
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public void delete(@RequestParam Long id) {

        foodService.delete(id);
    }

    @RequestMapping(value = "/change", method = RequestMethod.PUT)
    public void update(@RequestBody @Valid FoodDTO foodDTO, @RequestParam Long id) {

        foodService.update(foodDTO, id);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<FoodDTO> list() {
        return foodService.findAll();
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public FoodDTO findById(@RequestParam Long id) {
        return foodService.findById(id);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<FoodDTO> search(@RequestParam int dogAge, @RequestParam String protein){

        return foodService.search(dogAge,protein);
    }
}
