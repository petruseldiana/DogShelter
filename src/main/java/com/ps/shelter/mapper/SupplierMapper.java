package com.ps.shelter.mapper;

import com.ps.shelter.dto.SupplierDTO;
import com.ps.shelter.entity.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

@Mapper
public abstract class SupplierMapper {

    @Autowired
    EntityManager entityManager;

    @Mapping(target = "id", ignore = true)
    public abstract Supplier toEntity(SupplierDTO supplierDTO);

    public abstract SupplierDTO toDTO(Supplier supplier);
}
