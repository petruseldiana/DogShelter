package com.ps.shelter.service;

import com.ps.shelter.dto.FoodDTO;

import java.util.List;

public interface FoodService {

    FoodDTO save(FoodDTO foodDTO, String supplierName, String supplierEmail);

    void delete(Long id);

    void update(FoodDTO foodDTO, Long id);

    List<FoodDTO> findAll();

    FoodDTO findById(Long id);

    List<FoodDTO> search(int dogAge, String protein);
}
