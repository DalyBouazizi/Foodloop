package dev.Foodloop.persistance.dao;

import dev.Foodloop.persistance.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
