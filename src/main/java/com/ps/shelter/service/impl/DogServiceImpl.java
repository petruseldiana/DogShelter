package com.ps.shelter.service.impl;

import com.ps.shelter.dto.DiscountDTO;
import com.ps.shelter.dto.DogDTO;
import com.ps.shelter.dto.DogUpdateDTO;
import com.ps.shelter.entity.Discount;
import com.ps.shelter.entity.Dog;
import com.ps.shelter.entity.User;
import com.ps.shelter.mapper.DogMapper;
import com.ps.shelter.repository.DiscountRepository;
import com.ps.shelter.repository.DogRepository;
import com.ps.shelter.repository.UserRepository;
import com.ps.shelter.service.DiscountService;
import com.ps.shelter.service.DogService;
import com.ps.shelter.service.report.Report;
import com.ps.shelter.service.report.ReportFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DogServiceImpl implements DogService {

    private final DogMapper dogMapper;
    private final DogRepository dogRepository;
    private final UserRepository userRepository;
    private final DiscountRepository discountRepository;
    private final DiscountService discountService;

    @Autowired
    public DogServiceImpl(DogMapper dogMapper, DogRepository dogRepository, UserRepository userRepository, DiscountRepository discountRepository, DiscountService discountService) {
        this.dogMapper = dogMapper;
        this.dogRepository = dogRepository;
        this.userRepository = userRepository;
        this.discountRepository = discountRepository;
        this.discountService = discountService;
    }

    @Override
    public DogDTO save(DogDTO dogDTO){

        dogRepository.findByNameAndBreedAndStatus(dogDTO.getName(), dogDTO.getBreed(), dogDTO.getStatus())
                .ifPresent( s -> { throw new EntityNotFoundException("Dog already exists"); } );

        return dogMapper.toDTO(dogRepository.save(dogMapper.toEntity(dogDTO)));
    }

    @Override
    public void delete(Long id) {

        dogRepository.findById(id)
                .ifPresentOrElse(
                        dogRepository::delete,
                        () -> { throw new EntityNotFoundException("Cannot find dog with ID: " + id); }  );
    }

    @Override
    public void update(DogUpdateDTO dogUpdateDTO, Long id) {
        final Dog dog = dogRepository.findById(id)
                .orElseThrow(() -> { throw new EntityNotFoundException("Cannot find dog with ID: " + id); });

        dog.setStatus(dogUpdateDTO.getStatus());

        dogRepository.save(dog);
    }

    @Override
    public List<DogDTO> findAll() {

        return dogRepository.findAll().
                stream().
                map(dogMapper::toDTO).
                collect(Collectors.toList());
    }

    @Override
    public DogDTO finById(Long id) {
        final Dog dog = dogRepository.findById(id)
                .orElseThrow(() -> { throw new EntityNotFoundException("Cannot find dog with ID: " + id); });

        return dogMapper.toDTO(dog);
    }

    @Override
    public void adopt(DogDTO dogDTO, Long userId) {

        final Dog dog = dogRepository.findByNameAndBreedAndStatus(dogDTO.getName(), dogDTO.getBreed(),dogDTO.getStatus())
                .orElseThrow(() -> { throw new EntityNotFoundException("Cannot find dog with given info"); });

        if(dog.getStatus().equals("adopted")){

            throw new EntityNotFoundException("Dog is not available");
        }

        dog.setStatus("adopted");

        dogRepository.save(dog);

        if(dog.getBreed().equals("stray")){

            //generam un cod promotional
            final User user = userRepository.findById(userId)
                    .orElseThrow(() -> { throw new EntityNotFoundException("Cannot find dog with ID: " + userId); });

            List<DiscountDTO> discountDTOS = discountService.findAll();
            Long discountRandom = (long) (Math.random() * ((discountDTOS.size() - 2) + 1)) + 2;

            final Discount discount = discountRepository.findById(discountRandom)
                    .orElseThrow(() -> { throw new EntityNotFoundException("Cannot find discount with ID: " + discountRandom); });

            user.setDiscount(discount);
            userRepository.save(user);
        }
    }

    @Override
    public void generateReport(String reportType) {

        List<DogDTO> dogDTOS = dogRepository.findAll()
                .stream()
                .map(dogMapper::toDTO)
                .filter(dogDTO -> dogDTO.getStatus().equals("adopted"))
                .collect(Collectors.toList());

        ReportFactory reportFactory = new ReportFactory();

        Report report = reportFactory.getReport(reportType);

        report.generateReport(dogDTOS);
    }
}
