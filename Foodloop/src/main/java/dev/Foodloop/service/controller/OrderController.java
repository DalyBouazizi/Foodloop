package dev.Foodloop.service.controller;

import dev.Foodloop.persistance.entities.Order;
import dev.Foodloop.persistance.entities.OrderItem;
import dev.Foodloop.service.interfaces.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:4200") // Allow requests from Angular

public class OrderController {

    private final IOrderService orderService;

    @Autowired
    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    // Get all orders
    @GetMapping()
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    // Get order by ID
    @GetMapping({"/{id}"})
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    // Get orders by buyer ID
    @GetMapping({"/buyer/{buyerId}"})
    public ResponseEntity<List<Order>> getOrdersByBuyerId(@PathVariable Long buyerId) {
        List<Order> orders = orderService.getOrdersByBuyer(buyerId);
        return ResponseEntity.ok(orders);
    }

    // Get orders by restaurant ID
    @GetMapping({"/restaurant/{restaurantId}"})
    public ResponseEntity<List<Order>> getOrdersByRestaurantId(@PathVariable Long restaurantId) {
        List<Order> orders = orderService.getOrdersByRestaurant(restaurantId);
        return ResponseEntity.ok(orders);
    }

    // Create a new order
    @PostMapping({"/create/{buyerId}/{restaurantId}"})
    public ResponseEntity<Order> createOrder(@PathVariable Long buyerId,
                                             @PathVariable Long restaurantId,
                                             @RequestBody List<OrderItem> orderItems) {
        Order createdOrder = orderService.createOrder(buyerId, restaurantId, orderItems);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    // Update the status of an order
    @PutMapping({"/updateStatus/{id}"})
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long id, @RequestBody String status) {
        Order updatedOrder = orderService.updateOrderStatus(id, status);
        return ResponseEntity.ok(updatedOrder);
    }

    // Update an order (entire update)
    @PutMapping({"/update/{id}"})
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order order) {
        Order updatedOrder = orderService.updateOrder(id, order);
        return ResponseEntity.ok(updatedOrder);
    }

    // Delete an order by ID
    @DeleteMapping({"/delete/{id}"})
    public ResponseEntity<Boolean> deleteOrder(@PathVariable Long id) {
        boolean isDeleted = orderService.deleteOrder(id);
        return ResponseEntity.ok(isDeleted);
    }
}
