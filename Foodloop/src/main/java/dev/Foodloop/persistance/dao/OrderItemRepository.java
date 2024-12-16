package dev.Foodloop.persistance.dao;

import dev.Foodloop.persistance.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
