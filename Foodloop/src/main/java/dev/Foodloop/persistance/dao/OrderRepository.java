package dev.Foodloop.persistance.dao;

import dev.Foodloop.persistance.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {


    List<Order> findByBuyerId(Long buyerId);

    List<Order> findByRestaurantId(Long restaurantId);

}
