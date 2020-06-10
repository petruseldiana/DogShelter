package com.ps.shelter.service;

import com.ps.shelter.dto.DogDTO;
import com.ps.shelter.dto.DogUpdateDTO;
import com.ps.shelter.entity.Dog;

import java.util.List;
import java.util.Optional;

public interface DogService {

    DogDTO save(DogDTO dogDTO);

    void delete(Long id);

    void update(DogUpdateDTO dogUpdateDTO, Long id);

    List<DogDTO> findAll();

    DogDTO finById(Long id);

    void adopt(DogDTO dogDTO, Long userId);

    void generateReport(String reportType);
}
