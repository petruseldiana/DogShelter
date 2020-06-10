package com.ps.shelter.repository;

import com.ps.shelter.entity.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DogRepository extends JpaRepository<Dog, Long> {

    Optional<Dog> findByNameAndBreedAndStatus(String name, String breed, String status);

    Optional<Dog> findByStatus(String status);
}
