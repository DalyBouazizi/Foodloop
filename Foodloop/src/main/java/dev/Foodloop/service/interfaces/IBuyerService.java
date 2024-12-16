package dev.Foodloop.service.interfaces;

import dev.Foodloop.persistance.entities.Buyer;

import java.util.List;

public interface IBuyerService {

    Buyer createBuyer(Buyer buyer);

    Buyer updateBuyer(Long id, Buyer buyer);

    boolean deleteBuyer(Long id);

    Buyer getBuyerById(Long id);

    List<Buyer> getAllBuyers();
}
