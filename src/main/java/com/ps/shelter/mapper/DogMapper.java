package com.ps.shelter.mapper;

import com.ps.shelter.dto.DogDTO;
import com.ps.shelter.dto.NameIdDTO;
import com.ps.shelter.entity.Dog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

@Mapper
public abstract class DogMapper {

    @Autowired
    EntityManager entityManager;

    @Mapping(target = "id", ignore = true)
    public abstract Dog toEntity(DogDTO dogDTO);

    public abstract DogDTO toDTO(Dog dog);

}
