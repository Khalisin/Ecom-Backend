package com.store.Ecom.Service;

import com.store.Ecom.Entity.OrderItem;
import com.store.Ecom.Repository.OrderItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;

    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    public Optional<OrderItem> getOrderItemById(Long id) {
        return orderItemRepository.findById(id);
    }

    @Transactional
    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    public OrderItem updateOrderItem(Long id, OrderItem orderItemDetails) {
        return orderItemRepository.findById(id).map((orderItem -> {
            orderItem.setOrder(orderItemDetails.getOrder());
            orderItem.setProduct(orderItemDetails.getProduct());
            orderItem.setQuantity(orderItemDetails.getQuantity());
            return orderItemRepository.save(orderItem);
        })).orElseThrow(() -> new RuntimeException("Order item not found with id " + id));
    }

    public void deleteOrderItem(Long id) {
        orderItemRepository.deleteById(id);
    }
}
