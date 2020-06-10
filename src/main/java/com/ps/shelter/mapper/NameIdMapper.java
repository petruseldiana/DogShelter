package com.ps.shelter.mapper;

import com.ps.shelter.dto.NameIdDTO;
import com.ps.shelter.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface NameIdMapper {

    @Mapping(source = "username", target = "name")
    NameIdDTO userToNameIdDTO(User user);
}
