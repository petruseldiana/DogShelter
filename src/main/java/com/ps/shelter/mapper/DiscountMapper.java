package com.ps.shelter.mapper;

import com.ps.shelter.dto.DiscountDTO;
import com.ps.shelter.entity.Discount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

@Mapper
public abstract class DiscountMapper {

    @Autowired
    EntityManager entityManager;

    @Mapping(target = "id", ignore = true)
    public abstract Discount toEntity(DiscountDTO discountDTO);

    public abstract DiscountDTO toDTO(Discount discount);
}
