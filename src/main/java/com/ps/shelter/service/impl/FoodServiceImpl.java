package com.ps.shelter.service.impl;

import com.ps.shelter.dto.FoodDTO;
import com.ps.shelter.entity.Food;
import com.ps.shelter.entity.Supplier;
import com.ps.shelter.mapper.FoodMapper;
import com.ps.shelter.repository.FoodRepository;
import com.ps.shelter.repository.SupplierRepository;
import com.ps.shelter.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl implements FoodService {

    private final FoodMapper foodMapper;
    private final FoodRepository foodRepository;
    private final SupplierRepository supplierRepository;

    @Autowired
    public FoodServiceImpl(FoodMapper foodMapper, FoodRepository foodRepository, SupplierRepository supplierRepository) {
        this.foodMapper = foodMapper;
        this.foodRepository = foodRepository;
        this.supplierRepository = supplierRepository;
    }

    @Override
    public FoodDTO save(FoodDTO foodDTO, String supplierName, String supplierEmail) {

        Optional<Food> foodOptional = foodRepository.findByTypeAndDogAgeAndProtein(foodDTO.getType(), foodDTO.getDogAge(), foodDTO.getProtein());

        if(foodOptional.isPresent()){

            Food food = foodOptional.get();
            food.setQuantity(food.getQuantity() + foodDTO.getQuantity());

            return foodMapper.toDTO(foodRepository.save(food));
        }else{

            final Supplier supplier = supplierRepository.findByNameAndEmail(supplierName,supplierEmail)
                    .orElseThrow(() -> { throw new EntityNotFoundException("Cannot find supplier with given info"); } );

            Food newFood = foodMapper.toEntity(foodDTO);

            assert false;
            newFood.setSupplier(supplier);

            return foodMapper.toDTO(foodRepository.save(newFood));
        }
    }

    @Override
    public void delete(Long id) {

        foodRepository.findById(id)
                .ifPresentOrElse(
                        foodRepository::delete,
                        () -> {throw new EntityNotFoundException("The food with id " + id + " does not exist"); } );
    }

    @Override
    public void update(FoodDTO foodDTO, Long id) {

        final Food food = foodRepository.findById(id)
                .orElseThrow(() -> { throw new EntityNotFoundException("Cannot find food with ID: " + id); } );

       food.setType(foodDTO.getType());
       food.setDogAge(foodDTO.getDogAge());
       food.setProtein(foodDTO.getProtein());
       food.setQuantity(foodDTO.getQuantity());

       foodRepository.save(food);
    }

    @Override
    public List<FoodDTO> findAll() {

        return foodRepository.findAll().
                stream().
                map(foodMapper::toDTO).
                collect(Collectors.toList());
    }

    @Override
    public FoodDTO findById(Long id) {

        final Food food = foodRepository.findById(id)
                .orElseThrow(() -> { throw new EntityNotFoundException("Cannot find food with ID: " + id); } );

        return foodMapper.toDTO(food);
    }

    @Override
    public List<FoodDTO> search(int dogAge, String protein) {

        List<Food> foods = foodRepository.findAll();

        List<FoodDTO> result = foods.stream()
                .filter(c -> c.getDogAge() == dogAge && c.getProtein().equals(protein))
                .map(foodMapper::toDTO)
                .collect(Collectors.toList());

        return result;
    }
}
