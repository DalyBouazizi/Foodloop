package dev.Foodloop.persistance.dao;

import dev.Foodloop.persistance.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
