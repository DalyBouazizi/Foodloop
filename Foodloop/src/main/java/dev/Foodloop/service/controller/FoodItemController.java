package dev.Foodloop.service.controller;

import dev.Foodloop.persistance.entities.FoodItem;
import dev.Foodloop.service.interfaces.IFoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fooditems")
@CrossOrigin(origins = "http://localhost:4200") // Allow requests from Angular
public class FoodItemController {

    private final IFoodItemService foodItemService;

    @Autowired
    public FoodItemController(IFoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    @PostMapping("/create")
    public ResponseEntity<FoodItem> createFoodItem(@RequestBody FoodItem foodItem) {
        return ResponseEntity.status(HttpStatus.CREATED).body(foodItemService.createFoodItem(foodItem));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<FoodItem> updateFoodItem(@PathVariable Long id, @RequestBody FoodItem foodItem) {
        return ResponseEntity.ok(foodItemService.updateFoodItem(id, foodItem));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteFoodItem(@PathVariable Long id) {
        return ResponseEntity.ok(foodItemService.deleteFoodItem(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodItem> getFoodItemById(@PathVariable Long id) {
        return ResponseEntity.ok(foodItemService.getFoodItemById(id));
    }

    @GetMapping
    public ResponseEntity<List<FoodItem>> getAllFoodItems() {
        return ResponseEntity.ok(foodItemService.getAllFoodItems());
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<FoodItem>> getFoodItemsByRestaurant(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(foodItemService.getFoodItemsByRestaurantId(restaurantId));
    }
}
