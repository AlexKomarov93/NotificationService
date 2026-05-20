package com.example.NotificationService.repositories;

import com.example.NotificationService.entity.NotificationOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<NotificationOrder, Long> {

    List<NotificationOrder> findByOrderId(Long orderId);

    List<NotificationOrder> findByUserId(Long userId);

}

