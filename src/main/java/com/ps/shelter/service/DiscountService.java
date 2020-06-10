package com.ps.shelter.service;

import com.ps.shelter.dto.DiscountDTO;

import java.util.List;

public interface DiscountService {

    DiscountDTO save(DiscountDTO discountDTO);

    void delete(Long id);

    void update(DiscountDTO discountDTO, Long id);

    DiscountDTO findById(Long id);

    List<DiscountDTO> findAll();
}
