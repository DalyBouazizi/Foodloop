package dev.Foodloop.service.interfaces;

import dev.Foodloop.persistance.entities.Order;
import dev.Foodloop.persistance.entities.OrderItem;

import java.util.List;

public interface IOrderService {

    Order createOrder(Long buyerId, Long restaurantId, List<OrderItem> orderItems);

    Order updateOrder(Long id, Order updatedOrder);

    boolean deleteOrder(Long id);

    Order getOrderById(Long id);

    List<Order> getOrdersByBuyer(Long buyerId);

    List<Order> getOrdersByRestaurant(Long restaurantId);

    List<Order> getAllOrders();

    Order updateOrderStatus(Long id, String status);

    double calculateTotalPrice(Long id);

}
