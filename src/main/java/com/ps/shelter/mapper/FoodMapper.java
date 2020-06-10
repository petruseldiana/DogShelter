package com.ps.shelter.mapper;

import com.ps.shelter.dto.FoodDTO;
import com.ps.shelter.entity.Food;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

@Mapper
public abstract class FoodMapper {

    @Autowired
    EntityManager entityManager;

    @Mapping(target = "supplier", ignore = true)
    public abstract Food toEntity(FoodDTO foodDTO);

    public abstract FoodDTO toDTO(Food food);
}
