package dev.Foodloop.service.impliments;

import dev.Foodloop.persistance.dao.OrderRepository;
import dev.Foodloop.persistance.dao.PaymentRepository;
import dev.Foodloop.persistance.entities.Order;
import dev.Foodloop.persistance.entities.Payment;
import dev.Foodloop.service.interfaces.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PaymentService implements IPaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, OrderRepository orderRepository) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
    }

    // 1️⃣ Create Payment

    public Payment createPayment(Payment payment) {
        // Set the payment date to now if not provided
        if (payment.getPaymentDate() == null) {
            payment.setPaymentDate(LocalDate.now());
        }
        // Ensure the associated order exists
        Long orderId = payment.getOrder().getId();
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order with id " + orderId + " not found"));

        payment.setOrder(order);
        return paymentRepository.save(payment);
    }

    // 2️⃣ Update Payment
    @Override
    public Payment updatePayment(Long id, Payment updatedPayment) {
        Payment existingPayment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment with id " + id + " not found"));

        // Update payment fields
        existingPayment.setPaymentDate(updatedPayment.getPaymentDate());
        existingPayment.setAmount(updatedPayment.getAmount());
        existingPayment.setPaymentMethod(updatedPayment.getPaymentMethod());
        existingPayment.setStatus(updatedPayment.getStatus());

        // Update associated order if provided
        if (updatedPayment.getOrder() != null) {
            Long orderId = updatedPayment.getOrder().getId();
            Order order = orderRepository.findById(orderId)
                    .orElseThrow(() -> new RuntimeException("Order with id " + orderId + " not found"));
            existingPayment.setOrder(order);
        }

        return paymentRepository.save(existingPayment);
    }

    // 3️⃣ Delete Payment
    @Override
    public boolean deletePayment(Long id) {
        if (paymentRepository.existsById(id)) {
            paymentRepository.deleteById(id);
            return true;
        }
        throw new RuntimeException("Payment with id " + id + " not found");
    }

    // 4️⃣ Get Payment by ID
    @Override
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment with id " + id + " not found"));
    }

    // 5️⃣ Get All Payments
    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    // 6️⃣ Get Payments by Order ID
    @Override
    public List<Payment> getPaymentsByOrderId(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order with id " + orderId + " not found"));

        return paymentRepository.findByOrder(order);
    }
}