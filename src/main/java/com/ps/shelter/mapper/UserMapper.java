package com.ps.shelter.mapper;

import com.ps.shelter.dto.UserDTO;
import com.ps.shelter.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

@Mapper
public abstract class UserMapper {

    @Autowired
    EntityManager entityManager;

    @Mapping(target = "discount", ignore = true)
    public abstract User toEntity(UserDTO userDTO);

    @Mapping(target = "discountId", source = "discount.id")
    public abstract UserDTO toDTO(User user);
}
