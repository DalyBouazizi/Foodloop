package dev.Foodloop.persistance.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@DiscriminatorValue("BUYER")
public class Buyer extends User {

    @OneToOne(cascade = CascadeType.ALL)
    private Cart cart;

    @OneToMany(mappedBy = "buyer")
    private List<Order> orderHistory;

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public List<Order> getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(List<Order> orderHistory) {
        this.orderHistory = orderHistory;
    }
}

