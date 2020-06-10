package com.ps.shelter.service.impl;

import com.ps.shelter.dto.DiscountDTO;
import com.ps.shelter.entity.Discount;
import com.ps.shelter.mapper.DiscountMapper;
import com.ps.shelter.repository.DiscountRepository;
import com.ps.shelter.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepository discountRepository;
    private final DiscountMapper discountMapper;

    @Autowired
    public DiscountServiceImpl(DiscountRepository discountRepository, DiscountMapper discountMapper) {
        this.discountRepository = discountRepository;
        this.discountMapper = discountMapper;
    }

    @Override
    public DiscountDTO save(DiscountDTO discountDTO) {
        discountRepository.findDiscountByCode(discountDTO.getCode())
                .ifPresent( s -> { throw new EntityNotFoundException("Discount with code " + discountDTO.getCode() + " already exists"); } );

        return discountMapper.toDTO(discountRepository.save(discountMapper.toEntity(discountDTO)));
    }

    @Override
    public void delete(Long id) {

        discountRepository.findById(id)
                .ifPresentOrElse(
                        discountRepository::delete,
                        () -> { throw new EntityNotFoundException("Cannot find discount with ID: " + id); }  );
    }

    @Override
    public void update(DiscountDTO discountDTO, Long id) {

        final Discount discount = discountRepository.findById(id)
                .orElseThrow(() -> { throw new EntityNotFoundException("Cannot find discount with ID: " + id); } );

        discount.setCode(discountDTO.getCode());
        discount.setPercentage(discountDTO.getPercentage());

        discountRepository.save(discount);
    }

    @Override
    public DiscountDTO findById(Long id) {
        Discount discount = discountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid discount Id:" + id));

        return discountMapper.toDTO(discount);
    }

    @Override
    public List<DiscountDTO> findAll() {
        return discountRepository.findAll().
                stream().
                map(discountMapper::toDTO).
                collect(Collectors.toList());
    }
}
