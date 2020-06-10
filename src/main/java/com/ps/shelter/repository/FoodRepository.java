package com.ps.shelter.repository;

import com.ps.shelter.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

    Optional<Food> findByTypeAndDogAgeAndProtein(String type, int dogAge, String protein);
}
