package com.ps.shelter.controller;

import com.ps.shelter.dto.VeterinaryDTO;
import com.ps.shelter.service.VeterinaryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@RestController
@Api
@RequestMapping(value = "/vet")
public class VeterinaryRestController {

    private final VeterinaryService veterinaryService;

    @Autowired
    public VeterinaryRestController(VeterinaryService veterinaryService) {
        this.veterinaryService = veterinaryService;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void save(@RequestBody @Valid VeterinaryDTO veterinaryDTO) throws ParseException {

        veterinaryService.save(veterinaryDTO);
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public void delete(@RequestParam Long id) {

        veterinaryService.delete(id);
    }

    @RequestMapping(value = "/change", method = RequestMethod.PUT)
    public void update(@RequestBody @Valid VeterinaryDTO veterinaryDTO, @RequestParam Long id) {

        veterinaryService.update(veterinaryDTO, id);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<VeterinaryDTO> list() {
        return veterinaryService.findAll();
    }
}
