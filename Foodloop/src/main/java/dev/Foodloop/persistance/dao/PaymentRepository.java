package dev.Foodloop.persistance.dao;

import dev.Foodloop.persistance.entities.Order;
import dev.Foodloop.persistance.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByOrder(Order order);
}
