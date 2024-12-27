package com.example.demo.Controller;

import com.example.demo.Entity.Notifications;
import com.example.demo.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("auth")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/getAllNotification/{userid}")
    public List<Notifications> getAllNotifications(@PathVariable Long userid) {
        return notificationService.getAllNotifications(userid);
    }

    @GetMapping("/unread/{userid}")
    public List<Notifications> getUnreadNotifications(@PathVariable Long userid) {
        return notificationService.getUnreadNotifiByStudentId(userid);
    }

    @PutMapping("/{notifyID}/read")
    public Optional<Notifications> markAsRead(@PathVariable Long notifyID) {
        return notificationService.markAsRead(notifyID);
    }
}
