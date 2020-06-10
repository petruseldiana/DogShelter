package com.ps.shelter.controller;

import com.ps.shelter.dto.DogDTO;
import com.ps.shelter.dto.DogUpdateDTO;
import com.ps.shelter.entity.Dog;
import com.ps.shelter.service.DogService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController
@Api
@RequestMapping(value = "/dog")
public class DogRestController {

    private final DogService dogService;

    @Autowired
    public DogRestController(DogService dogService) {
        this.dogService = dogService;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void save(@RequestBody @Valid DogDTO dogDTO) throws ParseException {

        dogService.save(dogDTO);
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public void delete(@RequestParam Long id){

        dogService.delete(id);
    }

    @RequestMapping(value = "/change", method = RequestMethod.PUT)
    public void update(@RequestBody @Valid DogUpdateDTO dogUpdateDTO, @RequestParam Long id) {

        dogService.update(dogUpdateDTO, id);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<DogDTO> list() {
        return dogService.findAll();
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public DogDTO findById(@RequestParam Long id) {
        return dogService.finById(id);
    }

    @RequestMapping(value = "/adopt", method = RequestMethod.PUT)
    public void adopt(@RequestBody @Valid DogDTO dogDTO, @RequestParam Long userId){

        dogService.adopt(dogDTO, userId);
    }

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public void generateReport(@RequestParam String reportType){

        dogService.generateReport(reportType);
    }
}
