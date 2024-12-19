package dev.Foodloop.service.impliments;

import dev.Foodloop.persistance.dao.RestaurantRepository;
import dev.Foodloop.persistance.dao.UserRepository;
import dev.Foodloop.persistance.entities.Restaurant;
import dev.Foodloop.service.interfaces.IRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService implements IRestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository, UserRepository userRepository) {
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    // 1️⃣ Create Restaurant

    public Restaurant createRestaurant(Restaurant restaurant) {
        if (restaurant.getRole() == null) {
            restaurant.setRole("RESTAURANT");
        }
        if (userRepository.existsByEmail(restaurant.getEmail())) {
            throw new RuntimeException("Email already in use");
        }
        // Encrypt password before saving
        String encodedPassword = passwordEncoder.encode(restaurant.getPassword());
        restaurant.setPassword(encodedPassword);
        return restaurantRepository.save(restaurant);
    }

    // 2️⃣ Update Restaurant

    public Restaurant updateRestaurant(Long id, Restaurant updatedRestaurant) {
        Restaurant existingRestaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant with id " + id + " not found"));

        existingRestaurant.setUsername(updatedRestaurant.getUsername());
        if (updatedRestaurant.getPassword() != null && !updatedRestaurant.getPassword().isEmpty()) {
            String encodedPassword = passwordEncoder.encode(updatedRestaurant.getPassword());
            existingRestaurant.setPassword(encodedPassword);
        }
        existingRestaurant.setEmail(updatedRestaurant.getEmail());
        existingRestaurant.setRestaurantName(updatedRestaurant.getRestaurantName());
        existingRestaurant.setLocation(updatedRestaurant.getLocation());
        existingRestaurant.setInventory(updatedRestaurant.getInventory());
        existingRestaurant.setLogo(updatedRestaurant.getLogo());

        return restaurantRepository.save(existingRestaurant);
    }

    // 3️⃣ Delete Restaurant

    public boolean deleteRestaurant(Long id) {
        if (restaurantRepository.existsById(id)) {
            restaurantRepository.deleteById(id);
            return true;
        }
        throw new RuntimeException("Restaurant with id " + id + " not found");
    }

    // 4️⃣ Get Restaurant by ID

    public Restaurant getRestaurantById(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant with id " + id + " not found"));
    }

    // 5️⃣ Get All Restaurants

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }
}
