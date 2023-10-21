package com.mqtt.example.demo.services;

import com.mqtt.example.demo.dtos.MQTTNotificationMessage;

public interface NotificationService {

    void sendNotification(MQTTNotificationMessage message);
}
