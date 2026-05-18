package com.example.NotificationService.services;

import com.example.NotificationService.entity.NotificationOrder;
import com.example.NotificationService.repositories.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderNotificationServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderNotificationServiceImpl orderNotificationService;

    private List<NotificationOrder> orders;
    private NotificationOrder order1;
    private NotificationOrder order2;

    @BeforeEach
    public void setUp() {
        //Создание сущностей заказов
        //Первый заказ
        order1 = NotificationOrder.builder()
                .id(1L)
                .orderId(2L)
                .productId(3L)
                .quantity(34)
                .price(BigDecimal.valueOf(34.2))
                .sale(BigDecimal.valueOf(25.2))
                .totalPrice(BigDecimal.valueOf(14.9))
                .userId(5L)
                .build();
        //Второй заказ
        order2 = NotificationOrder.builder()
                .id(6L)
                .orderId(8L)
                .productId(7L)
                .quantity(2)
                .price(BigDecimal.valueOf(17.4))
                .sale(BigDecimal.valueOf(45.2))
                .totalPrice(BigDecimal.valueOf(67.9))
                .userId(56L)
                .build();
        orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);
    }

    @Test
    @DisplayName("Вывод всей таблицы заказов")
    public void shouldShowAllOrders() {
        // Arrange
        when(orderRepository.findAll()).thenReturn(orders);

        // Act
        List<NotificationOrder> result = orderNotificationService.getAllOrders();

        // Assert
        Assertions.assertNotNull(result); // Убеждаемся, что список физически существует (не null)
        Assertions.assertEquals(2, result.size()); // Проверяем, что вернулось именно 2 заказа
    }

    @Test
    @DisplayName("Вывод всех покупок по одному заказу")
    public void shouldShowAllProductsByOneOrder() {
        // Arrange
        when(orderRepository.findByOrderId(8L)).thenReturn(List.of(order2));

        // Act
        List<NotificationOrder> result = orderNotificationService.getAllProductsByOrder(8L);

        // Assert
        Assertions.assertNotNull(result); // Проверяем, что полученный список успешно инициализирован
        Assertions.assertEquals(1, result.size()); // Убеждаемся, что в ответе содержится ровно один заказ
        Assertions.assertEquals(8L, result.get(0).getOrderId()); // Проверяем, что ID заказа в ответе строго соответствует запрашиваемому
    }

    @Test
    @DisplayName("Вывод всех покупок по одному пользователю")
    public void shouldShowAllProductsByOneUser() {
        // Arrange
        when(orderRepository.findByUserId(56L)).thenReturn(List.of(order2));

        // Act
        List<NotificationOrder> result = orderNotificationService.getAllProductsByUser(56L);

        // Assert
        Assertions.assertNotNull(result); // Проверяем, что полученный список успешно инициализирован
        Assertions.assertEquals(1, result.size()); // Убеждаемся, что в ответе содержится ровно один заказ
        Assertions.assertEquals(56L, result.get(0).getUserId()); // Проверяем, что ID пользователя в ответе строго соответствует запрашиваемому
    }

}






