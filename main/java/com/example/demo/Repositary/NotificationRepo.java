package com.example.demo.Repositary;

import com.example.demo.Entity.Notifications;
import com.example.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;



import java.util.List;

public interface NotificationRepo extends JpaRepository<Notifications, Long> {

    List<Notifications> findByUser_IdAndIsRead(Long userId, boolean isRead);

    List<Notifications> findByUser_Id(Long userId);
}
