package com.ps.shelter.service.impl;

import com.ps.shelter.dto.VeterinaryDTO;
import com.ps.shelter.entity.Veterinary;
import com.ps.shelter.mapper.VeterinaryMapper;
import com.ps.shelter.repository.VeterinaryRepository;
import com.ps.shelter.service.VeterinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VeterinaryServiceImpl implements VeterinaryService {

    private final VeterinaryRepository veterinaryRepository;
    private final VeterinaryMapper veterinaryMapper;

    @Autowired
    public VeterinaryServiceImpl(VeterinaryRepository veterinaryRepository, VeterinaryMapper veterinaryMapper) {
        this.veterinaryRepository = veterinaryRepository;
        this.veterinaryMapper = veterinaryMapper;
    }

    @Override
    public VeterinaryDTO save(VeterinaryDTO veterinaryDTO) {

        veterinaryRepository.findByNameOrEmail(veterinaryDTO.getName(),  veterinaryDTO.getEmail())
                .ifPresent( s -> { throw new EntityNotFoundException("Veterinary with name " + veterinaryDTO.getName() + " or email " + veterinaryDTO.getEmail() + " already exists");} );

        return veterinaryMapper.toDTO(veterinaryRepository.save(veterinaryMapper.toEntity(veterinaryDTO)));
    }

    @Override
    public void delete(Long id) {

        veterinaryRepository.findById(id)
                .ifPresentOrElse(
                        veterinaryRepository::delete,
                        () -> {throw new EntityNotFoundException("Cannot find vet with ID: " + id); } );

    }

    @Override
    public void update(VeterinaryDTO veterinaryDTO, Long id) {

        final Veterinary veterinary = veterinaryRepository.findById(id)
                .orElseThrow(() -> { throw new EntityNotFoundException("Cannot find vet with ID: " + id); } );

        veterinary.setName(veterinaryDTO.getName());
        veterinary.setAddress(veterinaryDTO.getAddress());
        veterinary.setEmail(veterinaryDTO.getEmail());
        veterinary.setPhone(veterinaryDTO.getPhone());

        veterinaryRepository.save(veterinary);
    }

    @Override
    public List<VeterinaryDTO> findAll() {

        return veterinaryRepository.findAll().
                stream().
                map(veterinaryMapper::toDTO).
                collect(Collectors.toList());
    }
}
