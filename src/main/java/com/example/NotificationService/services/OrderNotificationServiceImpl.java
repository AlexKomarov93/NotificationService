package com.example.NotificationService.services;

import com.example.NotificationService.entity.NotificationOrder;
import com.example.NotificationService.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderNotificationServiceImpl implements OrderNotificationService {

    private final OrderRepository orderRepository;

    @Override
    public List<NotificationOrder> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<NotificationOrder> getAllProductsByOrder(Long orderId) {
        return orderRepository.findByOrderId(orderId);
    }

    @Override
    public List<NotificationOrder> getAllProductsByUser(Long userId) {
        return orderRepository.findByUserId(userId);
    }

}
