package com.ps.shelter.service.impl;

import com.ps.shelter.dto.SupplierDTO;
import com.ps.shelter.entity.Supplier;
import com.ps.shelter.mapper.SupplierMapper;
import com.ps.shelter.repository.SupplierRepository;
import com.ps.shelter.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierMapper supplierMapper;
    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierServiceImpl(SupplierMapper supplierMapper, SupplierRepository supplierRepository) {
        this.supplierMapper = supplierMapper;
        this.supplierRepository = supplierRepository;
    }


    @Override
    public SupplierDTO save(SupplierDTO supplierDTO) {

        supplierRepository.findByNameOrEmail(supplierDTO.getName(),supplierDTO.getEmail())
                .ifPresent( s -> { throw new EntityNotFoundException("Supplier with name " + supplierDTO.getName() + " or email " + supplierDTO.getEmail() + " already exists"); } );

        return supplierMapper.toDTO(supplierRepository.save(supplierMapper.toEntity(supplierDTO)));
    }

    @Override
    public void delete(Long id) {

        supplierRepository.findById(id)
                .ifPresentOrElse(
                        supplierRepository::delete,
                        () -> { throw new EntityNotFoundException("Supplier with id " + id + " does not exist"); }  );
    }

    @Override
    public void update(SupplierDTO supplierDTO, Long id) {

        final Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> { throw new EntityNotFoundException("Cannot find supplier with ID: " + id); } );

        supplier.setName(supplierDTO.getName());
        supplier.setAddress(supplierDTO.getAddress());
        supplier.setEmail(supplierDTO.getEmail());
        supplier.setPhone(supplierDTO.getPhone());

        supplierRepository.save(supplier);
    }

    @Override
    public List<SupplierDTO> findAll() {

        return supplierRepository.findAll().
                stream().
                map(supplierMapper::toDTO).
                collect(Collectors.toList());
    }
}
