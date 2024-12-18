package dev.Foodloop.service.interfaces;
import dev.Foodloop.persistance.entities.Payment;

import java.util.List;

public interface IPaymentService {

    Payment createPayment(Payment payment);

    Payment updatePayment(Long id, Payment payment);

    boolean deletePayment(Long id);

    Payment getPaymentById(Long id);

    List<Payment> getAllPayments();

    List<Payment> getPaymentsByOrderId(Long orderId);

}
