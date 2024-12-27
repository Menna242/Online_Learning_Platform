package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "NotificationDB")
@NoArgsConstructor
@AllArgsConstructor
public class Notifications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NotifyID")
    private long notifyID;

    @ManyToOne
    @JoinColumn(name = "userid",referencedColumnName = "USER_ID",nullable = false)
    private User user;
    //to be able to check if he wants all notifications or just unread one
    @Column(name = "isRead")
    private boolean isRead;

    @Column(name = "content")
    private String content;

    @Column(name = "type")
    private String type; // "SYSTEM" or "EMAIL"
}
