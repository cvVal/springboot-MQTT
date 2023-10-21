package com.mqtt.example.demo.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mqtt.example.demo.config.MqttProperties;
import com.mqtt.example.demo.dtos.MQTTNotificationMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    private static final Logger LOG = LoggerFactory.getLogger(NotificationServiceImpl.class);

    private final MqttGateway mqttGateway;
    private final ObjectMapper objectMapper;
    private final MqttProperties mqttProperties;

    public NotificationServiceImpl(MqttGateway mqttGateway, ObjectMapper objectMapper, MqttProperties mqttProperties) {
        this.mqttGateway = mqttGateway;
        this.objectMapper = objectMapper;
        this.mqttProperties = mqttProperties;
    }

    @Override
    public void sendNotification(MQTTNotificationMessage message) {
        try {
            String mqttMessage = objectMapper.writeValueAsString(message);
            LOG.info("MQTT message {}", mqttMessage);
            mqttGateway.sendMessage(mqttMessage, mqttProperties.getTopic());
            LOG.info("Message sent");
        } catch (Exception e) {
            throw new RuntimeException("Error deserializing the mqtt message");
        }
    }
}
