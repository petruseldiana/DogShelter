package com.ps.shelter.service;

import com.ps.shelter.dto.VeterinaryDTO;

import java.util.List;

public interface VeterinaryService  {

    VeterinaryDTO save(VeterinaryDTO veterinaryDTO);

    void delete(Long id);

    void update(VeterinaryDTO veterinaryDTO, Long id);

    List<VeterinaryDTO> findAll();
}
