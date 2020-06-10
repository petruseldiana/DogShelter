package com.ps.shelter.repository;

import com.ps.shelter.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    Optional<Supplier> findByNameOrEmail(String name, String email);

    Optional<Supplier> findByNameAndEmail(String name, String email);
}
