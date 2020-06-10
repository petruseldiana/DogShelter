package com.ps.shelter.mapper;

import com.ps.shelter.dto.ScheduleDTO;
import com.ps.shelter.entity.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

@Mapper
public abstract class ScheduleMapper {

    @Autowired
    EntityManager entityManager;

    @Mapping(target = "id", ignore = true)
    public abstract Schedule toEntity(ScheduleDTO scheduleDTO);

    public abstract ScheduleDTO toDTO(Schedule schedule);
}
