package dev.Foodloop.service.impliments;

import dev.Foodloop.persistance.dao.BuyerRepository;
import dev.Foodloop.persistance.dao.OrderRepository;
import dev.Foodloop.persistance.dao.RestaurantRepository;
import dev.Foodloop.persistance.entities.Buyer;
import dev.Foodloop.persistance.entities.Order;
import dev.Foodloop.persistance.entities.OrderItem;
import dev.Foodloop.persistance.entities.Restaurant;
import dev.Foodloop.service.interfaces.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;
    private final BuyerRepository buyerRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository,
                        BuyerRepository buyerRepository,
                        RestaurantRepository restaurantRepository) {
        this.orderRepository = orderRepository;
        this.buyerRepository = buyerRepository;
        this.restaurantRepository = restaurantRepository;
    }

    // 1️⃣ Create Order

    public Order createOrder(Long buyerId, Long restaurantId, List<OrderItem> orderItems) {
        Buyer buyer = buyerRepository.findById(buyerId)
                .orElseThrow(() -> new RuntimeException("Buyer with id " + buyerId + " not found"));

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant with id " + restaurantId + " not found"));

        Order order = new Order();
        order.setBuyer(buyer);
        order.setRestaurant(restaurant);
        order.setOrderItems(orderItems);
        order.setOrderDate(LocalDate.now());
        order.setStatus("PENDING");

        double totalPrice = orderItems.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
        order.setTotalPrice(totalPrice);

        return orderRepository.save(order);
    }

    // 2️⃣ Update Order

    public Order updateOrder(Long id, Order updatedOrder) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order with id " + id + " not found"));

        if (updatedOrder.getStatus() != null) {
            existingOrder.setStatus(updatedOrder.getStatus());
        }
        if (updatedOrder.getOrderItems() != null) {
            existingOrder.setOrderItems(updatedOrder.getOrderItems());
        }
        return orderRepository.save(existingOrder);
    }

    // 3️⃣ Delete Order

    public boolean deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        }
        throw new RuntimeException("Order with id " + id + " not found");
    }

    // 4️⃣ Get Order by ID

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order with id " + id + " not found"));
    }

    // 5️⃣ Get Orders by Buyer

    public List<Order> getOrdersByBuyer(Long buyerId) {
        return orderRepository.findByBuyerId(buyerId);
    }

    // 6️⃣ Get Orders by Restaurant

    public List<Order> getOrdersByRestaurant(Long restaurantId) {
        return orderRepository.findByRestaurantId(restaurantId);
    }

    // 7️⃣ Get All Orders

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // 8️⃣ Update Order Status

    public Order updateOrderStatus(Long id, String status) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order with id " + id + " not found"));

        existingOrder.setStatus(status);
        return orderRepository.save(existingOrder);
    }

    // 9️⃣ Calculate Total Price of an Order
    @Override
    public double calculateTotalPrice(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order with id " + id + " not found"));

        return order.getOrderItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
    }
}