package com.example.demo.Service;

import com.example.demo.Entity.Notifications;
import com.example.demo.Entity.User;
import com.example.demo.Repositary.NotificationRepo;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepo notificationRepo;


    public void createNotification(User user, String content, String type) {
        Notifications notification = new Notifications();
        notification.setUser(user);
        notification.setContent(content);
        notification.setRead(false);
        notification.setType(type);  //  (SYSTEM أو EMAIL)

        notificationRepo.save(notification);
    }

    public List<Notifications> getAllNotifications(long userid) {
        return notificationRepo.findByUser_Id(userid);
    }

    public List<Notifications> getUnreadNotifiByStudentId(long userid) {
        return notificationRepo.findByUser_IdAndIsRead(userid, false);
    }


    // Optional because can return null
    public Optional<Notifications> markAsRead(Long notifyID) {
        Optional<Notifications> notification = notificationRepo.findById(notifyID);
        notification.ifPresent(notif -> {
            notif.setRead(true);
            notificationRepo.save(notif);
        });
        return notification;
    }
}
