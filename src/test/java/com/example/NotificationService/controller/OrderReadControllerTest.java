package com.example.NotificationService.controller;

import com.example.NotificationService.entity.NotificationOrder;
import com.example.NotificationService.services.OrderNotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderReadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OrderNotificationService orderNotificationService;

    private NotificationOrder testOrder;

    @BeforeEach
    public void setUp() {
        testOrder = NotificationOrder.builder()
                .id(1L)
                .orderId(100L)
                .productId(500L)
                .quantity(3)
                .price(BigDecimal.valueOf(100.0))
                .sale(BigDecimal.valueOf(10.0))
                .totalPrice(BigDecimal.valueOf(290.0))
                .userId(10L)
                .build();
    }

    @Test
    @DisplayName("GET /api/orders/all должен возвращать список всех заказов")
    public void shouldReturnAllOrders() throws Exception {
        // Arrange
        when(orderNotificationService.getAllOrders()).thenReturn(List.of(testOrder));

        // Act & Assert
        mockMvc.perform(get("/api/orders/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].orderId").value(100))
                .andExpect(jsonPath("$[0].totalPrice").value(290.0));
    }

    @Test
    @DisplayName("GET /api/orders?orderId=... должен фильтровать по ID заказа")
    public void shouldReturnOrdersByOrderId() throws Exception {
        // Arrange
        when(orderNotificationService.getAllProductsByOrder(100L)).thenReturn(List.of(testOrder));

        // Act & Assert
        mockMvc.perform(get("/api/orders").param("orderId", "100"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].orderId").value(100))
                .andExpect(jsonPath("$[0].productId").value(500));
    }

    @Test
    @DisplayName("GET /api/orders?userId=... должен фильтровать по ID пользователя")
    public void shouldReturnOrdersByUserId() throws Exception {
        // Arrange
        when(orderNotificationService.getAllProductsByUser(10L)).thenReturn(List.of(testOrder));

        // Act & Assert
        mockMvc.perform(get("/api/orders").param("userId", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userId").value(10))
                .andExpect(jsonPath("$[0].quantity").value(3));
    }
}
