package com.store.Ecom.Service;

import com.store.Ecom.Entity.Order;
import com.store.Ecom.Repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Transactional
    public Order createOrder(Order order) {
        return  orderRepository.save(order);
    }

    public Order updateOrderField(Long id, Map<String, Object> updates) {
        return orderRepository.findById(id).map((order) -> {
            updates.forEach((key, value) -> {
                switch (key) {
                    case "price":
                        order.setPrice((Double) value);
                        break;
                    case "status":
                        order.setStatus((String) value);
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid field: " + key);
                }
            });
            return orderRepository.save(order);
        }).orElseThrow(() -> new RuntimeException("Order not found with id " + id));
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
