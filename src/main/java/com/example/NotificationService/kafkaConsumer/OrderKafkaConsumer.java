package com.example.NotificationService.kafkaConsumer;

import com.example.NotificationService.dto.OrderEvent;
import com.example.NotificationService.entity.NotificationOrder;
import com.example.NotificationService.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderKafkaConsumer {

    private final OrderRepository orderRepository;

    @KafkaListener(topics = "${topic.send-order}", groupId = "${spring.kafka.consumer.group-id}")
    public void orderListen(OrderEvent event) {
        log.info("Получен заказ из OrderService: {}", event);

        NotificationOrder orderEntity = NotificationOrder.builder()
                .orderId(event.getOrderId())
                .productId(event.getProductId())
                .quantity(event.getQuantity())
                .price(event.getPrice())
                .sale(event.getSale())
                .totalPrice(event.getTotalPrice())
                .userId(event.getUserId())
                .build();

        orderRepository.save(orderEntity);
    }

}

