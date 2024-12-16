package dev.Foodloop.persistance.dao;

import dev.Foodloop.persistance.entities.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerRepository extends JpaRepository<Buyer,Long> {
}
