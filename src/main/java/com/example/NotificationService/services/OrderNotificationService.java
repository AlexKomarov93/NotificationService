package com.example.NotificationService.services;

import com.example.NotificationService.entity.NotificationOrder;
import java.util.List;

public interface OrderNotificationService {

    List<NotificationOrder> getAllOrders();

    List<NotificationOrder> getAllProductsByOrder(Long orderId);

    List<NotificationOrder> getAllProductsByUser(Long userId);

}
