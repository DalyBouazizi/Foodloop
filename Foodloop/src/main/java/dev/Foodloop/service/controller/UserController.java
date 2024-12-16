package dev.Foodloop.service.controller;

import dev.Foodloop.persistance.entities.Buyer;
import dev.Foodloop.persistance.entities.Restaurant;
import dev.Foodloop.service.impliments.BuyerService;
import dev.Foodloop.service.impliments.RestaurantService;
import dev.Foodloop.service.impliments.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final BuyerService buyerService;
    private final RestaurantService restaurantService;
    private final UserService userService;

    @Autowired
    public UserController(BuyerService buyerService, RestaurantService restaurantService, UserService userService) {
        this.buyerService = buyerService;
        this.restaurantService = restaurantService;
        this.userService = userService;
    }

    // ----------------------------------
    // Buyer CRUD Operations
    // ----------------------------------

    // 1️⃣ Create a Buyer (POST /users/buyers)
    @PostMapping("/buyers")
    public ResponseEntity<Buyer> createBuyer(@RequestBody Buyer buyer) {
        Buyer createdBuyer = buyerService.createBuyer(buyer);
        return new ResponseEntity<>(createdBuyer, HttpStatus.CREATED);
    }

    // 2️⃣ Get a Buyer by ID (GET /users/buyers/{id})
    @GetMapping("/buyers/{id}")
    public ResponseEntity<Buyer> getBuyerById(@PathVariable Long id) {
        Buyer buyer = buyerService.getBuyerById(id);
        return new ResponseEntity<>(buyer, HttpStatus.OK);
    }

    // 3️⃣ Get All Buyers (GET /users/buyers)
    @GetMapping("/buyers")
    public ResponseEntity<List<Buyer>> getAllBuyers() {
        List<Buyer> buyers = buyerService.getAllBuyers();
        return new ResponseEntity<>(buyers, HttpStatus.OK);
    }

    // 4️⃣ Update a Buyer (PUT /users/buyers/{id})
    @PutMapping("/buyers/{id}")
    public ResponseEntity<Buyer> updateBuyer(@PathVariable Long id, @RequestBody Buyer updatedBuyer) {
        Buyer buyer = buyerService.updateBuyer(id, updatedBuyer);
        return new ResponseEntity<>(buyer, HttpStatus.OK);
    }

    // 5️⃣ Delete a Buyer (DELETE /users/buyers/{id})
    @DeleteMapping("/buyers/{id}")
    public ResponseEntity<String> deleteBuyer(@PathVariable Long id) {
        buyerService.deleteBuyer(id);
        return new ResponseEntity<>("Buyer deleted successfully", HttpStatus.OK);
    }

    // ----------------------------------
    // Restaurant CRUD Operations
    // ----------------------------------

    // 1️⃣ Create a Restaurant (POST /users/restaurants)
    @PostMapping("/restaurants")
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant) {
        Restaurant createdRestaurant = restaurantService.createRestaurant(restaurant);
        return new ResponseEntity<>(createdRestaurant, HttpStatus.CREATED);
    }

    // 2️⃣ Get a Restaurant by ID (GET /users/restaurants/{id})
    @GetMapping("/restaurants/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable Long id) {
        Restaurant restaurant = restaurantService.getRestaurantById(id);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    // 3️⃣ Get All Restaurants (GET /users/restaurants)
    @GetMapping("/restaurants")
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    // 4️⃣ Update a Restaurant (PUT /users/restaurants/{id})
    @PutMapping("/restaurants/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable Long id, @RequestBody Restaurant updatedRestaurant) {
        Restaurant restaurant = restaurantService.updateRestaurant(id, updatedRestaurant);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    // 5️⃣ Delete a Restaurant (DELETE /users/restaurants/{id})
    @DeleteMapping("/restaurants/{id}")
    public ResponseEntity<String> deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
        return new ResponseEntity<>("Restaurant deleted successfully", HttpStatus.OK);
    }
//AUTHENTICATE
// Login a User
@PostMapping("/login")
public ResponseEntity<String> loginUser(@RequestParam String username, @RequestParam String password) {
    boolean isAuthenticated = userService.authenticateUser(username, password);

    if (isAuthenticated) {
        return ResponseEntity.ok("Login successful!");
    } else {
        return ResponseEntity.status(401).body("Invalid username or password");
    }
}
}
