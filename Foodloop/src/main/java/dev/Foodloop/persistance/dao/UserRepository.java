package dev.Foodloop.persistance.dao;

import dev.Foodloop.persistance.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email); // Check if an email already exists
}
