package dev.Foodloop.persistance.dao;

import dev.Foodloop.persistance.entities.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
