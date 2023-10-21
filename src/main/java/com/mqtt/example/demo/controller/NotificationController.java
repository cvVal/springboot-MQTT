package com.mqtt.example.demo.controller;

import com.mqtt.example.demo.dtos.MQTTNotificationMessage;
import com.mqtt.example.demo.services.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping(value = "/notification")
    public ResponseEntity<Void> alertNotification(@RequestBody MQTTNotificationMessage request) {
        notificationService.sendNotification(request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
