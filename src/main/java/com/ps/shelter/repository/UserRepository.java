package com.ps.shelter.repository;

import com.ps.shelter.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findUserByUsernameOrEmail(String username, String email);

    User findUserByUsername(String username);

    User findByUsernameAndEmail(String username, String email);
}
