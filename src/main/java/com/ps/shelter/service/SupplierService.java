package com.ps.shelter.service;

import com.ps.shelter.dto.SupplierDTO;

import java.util.List;

public interface SupplierService {

    SupplierDTO save(SupplierDTO supplierDTO);

    void delete(Long id);

    void update(SupplierDTO supplierDTO, Long id);

    List<SupplierDTO> findAll();
}
