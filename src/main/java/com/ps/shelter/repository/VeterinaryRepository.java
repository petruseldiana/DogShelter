package com.ps.shelter.repository;

import com.ps.shelter.entity.Veterinary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VeterinaryRepository extends JpaRepository<Veterinary, Long> {

    Optional<Veterinary> findByNameOrEmail(String name, String email);
}
