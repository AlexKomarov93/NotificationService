package com.example.NotificationService.controller;

import com.example.NotificationService.entity.NotificationOrder;
import com.example.NotificationService.services.OrderNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/orders")
public class OrderReadController {

    private final OrderNotificationService orderNotificationService;

    @GetMapping("/all")
    public List<NotificationOrder> getListOfProducts() {
        return orderNotificationService.getAllOrders();
    }

    @GetMapping(params = "orderId")
    public List<NotificationOrder> getAllProductsByOrderId(@RequestParam("orderId") Long orderId) {
        return orderNotificationService.getAllProductsByOrder(orderId);
    }

    @GetMapping(params = "userId")
    public List<NotificationOrder> getAllProductsByUserId(@RequestParam("userId") Long userId) {
        return orderNotificationService.getAllProductsByUser(userId);
    }

}













