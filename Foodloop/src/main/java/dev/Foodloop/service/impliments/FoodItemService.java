package dev.Foodloop.service.impliments;

import dev.Foodloop.persistance.dao.FoodItemRepository;
import dev.Foodloop.persistance.dao.RestaurantRepository;
import dev.Foodloop.persistance.entities.FoodItem;
import dev.Foodloop.persistance.entities.Restaurant;
import dev.Foodloop.service.interfaces.IFoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FoodItemService implements IFoodItemService {

    private final FoodItemRepository foodItemRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public FoodItemService(FoodItemRepository foodItemRepository, RestaurantRepository restaurantRepository) {
        this.foodItemRepository = foodItemRepository;
        this.restaurantRepository = restaurantRepository;
    }


    public FoodItem createFoodItem(FoodItem foodItem) {
        // Verify that the restaurant exists before saving the FoodItem
        if (foodItem.getRestaurant() == null || foodItem.getRestaurant().getId() == null) {
            throw new RuntimeException("Restaurant must be specified for the FoodItem");
        }
        Restaurant restaurant = restaurantRepository.findById(foodItem.getRestaurant().getId())
                .orElseThrow(() -> new RuntimeException("Restaurant with id " + foodItem.getRestaurant().getId() + " not found"));

        foodItem.setRestaurant(restaurant);
        return foodItemRepository.save(foodItem);
    }

    @Override
    public FoodItem updateFoodItem(Long id, FoodItem updatedFoodItem) {
        // Find the existing FoodItem
        FoodItem existingFoodItem = foodItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("FoodItem with id " + id + " not found"));

        // Update the fields
        existingFoodItem.setName(updatedFoodItem.getName());
        existingFoodItem.setDescription(updatedFoodItem.getDescription());
        existingFoodItem.setPrice(updatedFoodItem.getPrice());
        existingFoodItem.setQuantity(updatedFoodItem.getQuantity());
        existingFoodItem.setExpirationDate(updatedFoodItem.getExpirationDate());
        existingFoodItem.setPicture(updatedFoodItem.getPicture());

        return foodItemRepository.save(existingFoodItem);
    }


    public boolean deleteFoodItem(Long id) {
        if (foodItemRepository.existsById(id)) {
            foodItemRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }


    public FoodItem getFoodItemById(Long id) {
        return foodItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("FoodItem with id " + id + " not found"));
    }


    public List<FoodItem> getAllFoodItems() {
        return foodItemRepository.findAll();
    }


    public List<FoodItem> getFoodItemsByRestaurantId(Long restaurantId) {
        // Validate restaurant exists
        restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant with id " + restaurantId + " not found"));

        return foodItemRepository.findByRestaurantId(restaurantId);
    }

}
