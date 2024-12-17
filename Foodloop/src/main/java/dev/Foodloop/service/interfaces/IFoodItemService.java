package dev.Foodloop.service.interfaces;

import dev.Foodloop.persistance.entities.FoodItem;

import java.util.List;

public interface IFoodItemService {
    FoodItem createFoodItem(FoodItem foodItem);

    FoodItem updateFoodItem(Long id, FoodItem foodItem);

    boolean deleteFoodItem(Long id);

    FoodItem getFoodItemById(Long id);

    List<FoodItem> getAllFoodItems();

    List<FoodItem> getFoodItemsByRestaurantId(Long restaurantId);
}
