package dev.Foodloop.persistance.dao;

import dev.Foodloop.persistance.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
