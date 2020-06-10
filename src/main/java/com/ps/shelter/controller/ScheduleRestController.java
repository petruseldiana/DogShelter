package com.ps.shelter.controller;

import com.ps.shelter.dto.ScheduleDTO;
import com.ps.shelter.service.ScheduleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@RestController
@Api
@RequestMapping(value = "/schedule")
public class ScheduleRestController {

    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleRestController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void save(@RequestBody @Valid ScheduleDTO scheduleDTO) throws ParseException {

        scheduleService.save(scheduleDTO);
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public void delete(@RequestParam Long id) {

        scheduleService.delete(id);
    }

    @RequestMapping(value = "/change", method = RequestMethod.PUT)
    public void update(@RequestBody @Valid ScheduleDTO scheduleDTO, @RequestParam Long id) {

        scheduleService.update(scheduleDTO, id);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<ScheduleDTO> list() {
        return scheduleService.findAll();
    }
}
