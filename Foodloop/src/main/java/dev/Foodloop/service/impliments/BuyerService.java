package dev.Foodloop.service.impliments;

import dev.Foodloop.persistance.dao.BuyerRepository;
import dev.Foodloop.persistance.entities.Buyer;
import dev.Foodloop.service.interfaces.IBuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuyerService implements IBuyerService {

    private final BuyerRepository buyerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    public BuyerService(BuyerRepository buyerRepository) {
        this.buyerRepository = buyerRepository;
    }

    // 1️⃣ Create Buyer

    public Buyer createBuyer(Buyer buyer) {
        if (buyer.getRole() == null) {
            buyer.setRole("BUYER");
        }
        // Encrypt password before saving
        String encodedPassword = passwordEncoder.encode(buyer.getPassword());
        buyer.setPassword(encodedPassword);
        return buyerRepository.save(buyer);
    }

    // 2️⃣ Update Buyer

    public Buyer updateBuyer(Long id, Buyer updatedBuyer) {
        Buyer existingBuyer = buyerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Buyer with id " + id + " not found"));

        existingBuyer.setUsername(updatedBuyer.getUsername());
        existingBuyer.setPassword(updatedBuyer.getPassword());
        existingBuyer.setEmail(updatedBuyer.getEmail());
        existingBuyer.setCart(updatedBuyer.getCart());
        existingBuyer.setOrderHistory(updatedBuyer.getOrderHistory());

        return buyerRepository.save(existingBuyer);
    }

    // 3️⃣ Delete Buyer

    public boolean deleteBuyer(Long id) {
        if (buyerRepository.existsById(id)) {
            buyerRepository.deleteById(id);
            return true;
        }
        throw new RuntimeException("Buyer with id " + id + " not found");
    }

    // 4️⃣ Get Buyer by ID

    public Buyer getBuyerById(Long id) {
        return buyerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Buyer with id " + id + " not found"));
    }

    // 5️⃣ Get All Buyers

    public List<Buyer> getAllBuyers() {
        return buyerRepository.findAll();
    }
}
