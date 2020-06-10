package com.ps.shelter.service.impl;

import com.ps.shelter.dto.DiscountDTO;
import com.ps.shelter.dto.FoodDTO;
import com.ps.shelter.dto.ShoppingBasketDTO;
import com.ps.shelter.dto.ShoppingBasketFoodDTO;
import com.ps.shelter.entity.Food;
import com.ps.shelter.entity.ShoppingBasket;
import com.ps.shelter.entity.ShoppingBasketFood;
import com.ps.shelter.entity.User;
import com.ps.shelter.mapper.DiscountMapper;
import com.ps.shelter.mapper.ShoppingBasketMapper;
import com.ps.shelter.repository.FoodRepository;
import com.ps.shelter.repository.ShoppingBasketFoodRepository;
import com.ps.shelter.repository.ShoppingBasketRepository;
import com.ps.shelter.repository.UserRepository;
import com.ps.shelter.service.FoodService;
import com.ps.shelter.service.ShoppingBasketFoodService;
import com.ps.shelter.service.ShoppingBasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShoppingBasketServiceImpl implements ShoppingBasketService {

    private final ShoppingBasketRepository shoppingBasketRepository;
    private final ShoppingBasketFoodRepository shoppingBasketFoodRepository;
    private final FoodService foodService;
    private final FoodRepository foodRepository;
    private final ShoppingBasketMapper shoppingBasketMapper;
    private final UserRepository userRepository;
    private final ShoppingBasketFoodService shoppingBasketFoodService;
    private final DiscountMapper discountMapper;

    @Autowired
    public ShoppingBasketServiceImpl(ShoppingBasketRepository shoppingBasketRepository, ShoppingBasketFoodRepository shoppingBasketFoodRepository, FoodService foodService, FoodRepository foodRepository, ShoppingBasketMapper shoppingBasketMapper, UserRepository userRepository, ShoppingBasketFoodService shoppingBasketFoodService, DiscountMapper discountMapper) {
        this.shoppingBasketRepository = shoppingBasketRepository;
        this.shoppingBasketFoodRepository = shoppingBasketFoodRepository;
        this.foodService = foodService;
        this.foodRepository = foodRepository;
        this.shoppingBasketMapper = shoppingBasketMapper;
        this.userRepository = userRepository;
        this.shoppingBasketFoodService = shoppingBasketFoodService;
        this.discountMapper = discountMapper;
    }

    @Override
    public DiscountDTO checkout(Long shoppingBasketId) {

        List<ShoppingBasketFoodDTO> shoppingBasketFoods = shoppingBasketFoodService.findAllByShoppingBasketId(shoppingBasketId);

        for(ShoppingBasketFoodDTO sbf: shoppingBasketFoods){

            FoodDTO foodTable = foodService.findById(sbf.getFoodId());

            int quantity = foodTable.getQuantity() - sbf.getQuantity();

            foodTable.setQuantity(quantity);

            foodService.update(foodTable, sbf.getFoodId());

            shoppingBasketFoodRepository.deleteById(sbf.getId());
        }

        shoppingBasketFoods.clear();

        final ShoppingBasket shoppingBasket = shoppingBasketRepository.findById(shoppingBasketId)
                    .orElseThrow(() -> { throw new EntityNotFoundException("Cannot find shopping basket with ID: " + shoppingBasketId); } );

        return discountMapper.toDTO(shoppingBasket.getUser().getDiscount());
    }

    @Override
    public ShoppingBasketDTO create(ShoppingBasketDTO shoppingBasketDTO) {

        final User user = userRepository.findById(shoppingBasketDTO.getUser_id())
                .orElseThrow(() -> { throw new EntityNotFoundException("Cannot find user with ID: " + shoppingBasketDTO.getUser_id()); } );

        ShoppingBasket shoppingBasket = shoppingBasketRepository.findByUser(user);

        if(shoppingBasket != null){
            return null;
        }else{
            shoppingBasket = new ShoppingBasket();
        }

        shoppingBasket.setUser(user);
        return shoppingBasketMapper.toDTO(shoppingBasketRepository.save(shoppingBasket));
    }

    @Override
    public void addArticle(ShoppingBasketFoodDTO shoppingBasketFoodDTO) {

        final ShoppingBasket basket = shoppingBasketRepository.findById(shoppingBasketFoodDTO.getShoppingBasketId())
                .orElseThrow(() -> { throw new EntityNotFoundException("Cannot find basket with ID: " + shoppingBasketFoodDTO.getShoppingBasketId()); } );

        Optional<ShoppingBasketFood> shoppingBasketFoodOptional = basket.getShoppingBasketFoods()
                .stream()
                .filter(shoppingBasketItem -> shoppingBasketItem.getFood().getId().equals(shoppingBasketFoodDTO.getFoodId()))
                .findFirst();

        if (shoppingBasketFoodOptional.isPresent()) {

            //exista deja, adaugam noua cantitate la cea existenta
            ShoppingBasketFood shoppingBasketFood = shoppingBasketFoodOptional.get();

            int existingQuantity = shoppingBasketFood.getQuantity();
            shoppingBasketFood.setQuantity(existingQuantity + shoppingBasketFoodDTO.getQuantity());

            shoppingBasketFoodRepository.save(shoppingBasketFood);
        } else {

            //cream un subItem nou
            ShoppingBasketFood shoppingBasketFood = new ShoppingBasketFood();

            final Food food = foodRepository.findById(shoppingBasketFoodDTO.getFoodId())
                    .orElseThrow(() -> { throw new EntityNotFoundException("Cannot find food with ID: " + shoppingBasketFoodDTO.getFoodId()); } );

            shoppingBasketFood.setFood(food);
            shoppingBasketFood.setQuantity(shoppingBasketFoodDTO.getQuantity());
            shoppingBasketFood.setShoppingBasket(basket);

            shoppingBasketFoodRepository.save(shoppingBasketFood);
        }
    }

    @Override
    public void delete(Long shoppingBasketId) {

    }

    @Override
    public Optional<ShoppingBasket> findById(Long id) {

        return shoppingBasketRepository.findById(id);
    }

    @Override
    public ShoppingBasketDTO findByUserId(Long userId) {

        return shoppingBasketRepository.findAll()
                .stream()
                .map(shoppingBasketMapper::toDTO)
                .filter(shoppingBasket -> shoppingBasket.getUser_id().equals(userId))
                .findFirst()
                .get();
    }

    @Override
    public List<ShoppingBasketDTO> getAll() {
        return shoppingBasketRepository.findAll().
                stream().
                map(shoppingBasketMapper::toDTO).
                collect(Collectors.toList());
    }
}
