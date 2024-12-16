package dev.Foodloop.persistance.dao;

import dev.Foodloop.persistance.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
