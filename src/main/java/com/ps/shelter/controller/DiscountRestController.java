package com.ps.shelter.controller;

import com.ps.shelter.dto.DiscountDTO;
import com.ps.shelter.service.DiscountService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api
@RequestMapping(value = "/discount")
public class DiscountRestController {

    private final DiscountService discountService;

    @Autowired
    public DiscountRestController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void save(@RequestBody @Valid DiscountDTO discountDTO) {

        discountService.save(discountDTO);
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public void delete(@RequestParam Long id){

        discountService.delete(id);
    }

    @RequestMapping(value = "/change", method = RequestMethod.PUT)
    public void update(@RequestBody @Valid DiscountDTO discountDTO, @RequestParam Long id) {

        discountService.update(discountDTO, id);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<DiscountDTO> list() {
        return discountService.findAll();
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public DiscountDTO findById(@RequestParam Long id) {
        return discountService.findById(id);
    }
}
