package com.rajesh.service;

import com.rajesh.dto.OrderEvent;
import com.rajesh.entity.Order;
import com.rajesh.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReverseOrder {

    @Autowired
    private OrderRepository repository;

    @KafkaListener(topics = "reversed-orders", groupId = "orders-group")
    public void reverseOrder(OrderEvent orderEvent) {
        System.out.println("Inside reverse order-ms for order "+orderEvent);
        try {
            Optional<Order> order = repository.findById(orderEvent.getOrder().getOrderId());
            order.ifPresent(o -> {
                o.setStatus("FAILED");
                this.repository.save(o);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
