package com.ps.shelter.controller;

import com.ps.shelter.dto.SupplierDTO;
import com.ps.shelter.service.SupplierService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@RestController
@Api
@RequestMapping(value = "/supplier")
public class SupplierRestController {

    private final SupplierService supplierService;

    @Autowired
    public SupplierRestController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void save(@RequestBody @Valid SupplierDTO supplierDTO) throws ParseException {

        supplierService.save(supplierDTO);
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public void delete(@RequestParam Long id) {

        supplierService.delete(id);
    }

    @RequestMapping(value = "/change", method = RequestMethod.PUT)
    public void update(@RequestBody @Valid SupplierDTO supplierDTO, @RequestParam Long id) {

        supplierService.update(supplierDTO, id);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<SupplierDTO> list() {
        return supplierService.findAll();
    }
}
