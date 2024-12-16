package dev.Foodloop.service.interfaces;

import dev.Foodloop.persistance.entities.Restaurant;

import java.util.List;

public interface IRestaurantService {


    Restaurant createRestaurant(Restaurant restaurant);

    Restaurant updateRestaurant(Long id, Restaurant restaurant);

    boolean deleteRestaurant(Long id);

    Restaurant getRestaurantById(Long id);

    List<Restaurant> getAllRestaurants();

}
