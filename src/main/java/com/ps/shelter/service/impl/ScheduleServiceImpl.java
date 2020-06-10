package com.ps.shelter.service.impl;

import com.ps.shelter.dto.ScheduleDTO;
import com.ps.shelter.entity.Schedule;
import com.ps.shelter.mapper.ScheduleMapper;
import com.ps.shelter.repository.ScheduleRepository;
import com.ps.shelter.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleMapper scheduleMapper;
    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleServiceImpl(ScheduleMapper scheduleMapper, ScheduleRepository scheduleRepository) {
        this.scheduleMapper = scheduleMapper;
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleDTO save(ScheduleDTO scheduleDTO) {

        scheduleRepository.findByDay(scheduleDTO.getDay())
                .ifPresent( s -> {throw new EntityNotFoundException("Schedule with day " + scheduleDTO.getDay() + " already exists"); } );

        return scheduleMapper.toDTO(scheduleRepository.save(scheduleMapper.toEntity(scheduleDTO)));
    }

    @Override
    public void delete(Long id) {

        scheduleRepository.findById(id)
                .ifPresentOrElse(
                        scheduleRepository::delete,
                        () -> {throw new EntityNotFoundException("The schedule with id " + id + " does not exist"); } );
    }

    @Override
    public void update(ScheduleDTO scheduleDTO, Long id) {

        final Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> { throw new EntityNotFoundException("Cannot find schedule with ID: " + id); } );

       schedule.setDay(scheduleDTO.getDay());
       schedule.setStartHour(scheduleDTO.getStartHour());
       schedule.setStopHour(scheduleDTO.getStopHour());
       schedule.setDetails(scheduleDTO.getDetails());

        scheduleRepository.save(schedule);
    }

    @Override
    public List<ScheduleDTO> findAll() {

        return scheduleRepository.findAll().
                stream().
                map(scheduleMapper::toDTO).
                collect(Collectors.toList());
    }
}
