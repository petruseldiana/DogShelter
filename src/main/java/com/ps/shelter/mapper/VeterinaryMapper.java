package com.ps.shelter.mapper;

import com.ps.shelter.dto.VeterinaryDTO;
import com.ps.shelter.entity.Veterinary;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

@Mapper
public abstract class VeterinaryMapper {

    @Autowired
    EntityManager entityManager;

    @Mapping(target = "id", ignore = true)
    public abstract Veterinary toEntity(VeterinaryDTO veterinaryDTO);

    public abstract VeterinaryDTO toDTO(Veterinary veterinary);
}