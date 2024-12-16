package dev.Foodloop.persistance.dao;

import dev.Foodloop.persistance.entities.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodItemRepository  extends JpaRepository<FoodItem, Long> {
}
