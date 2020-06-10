package com.ps.shelter.service;

import com.ps.shelter.dto.ScheduleDTO;

import java.util.List;

public interface ScheduleService {

    ScheduleDTO save(ScheduleDTO scheduleDTO);

    void delete(Long id);

    void update(ScheduleDTO scheduleDTO, Long id);

    List<ScheduleDTO> findAll();
}
