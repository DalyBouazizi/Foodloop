package dev.Foodloop.persistance.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
@DiscriminatorValue("RESTAURANT")
public class Restaurant extends User {

    private String restaurantName;
    private String location;
    private String logo;

    @OneToMany(mappedBy = "restaurant")
    private List<FoodItem> inventory;

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<FoodItem> getInventory() {
        return inventory;
    }

    public void setInventory(List<FoodItem> inventory) {
        this.inventory = inventory;
    }
}
