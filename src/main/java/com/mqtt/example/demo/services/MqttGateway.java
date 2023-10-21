package com.mqtt.example.demo.services;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;

@MessagingGateway(defaultRequestChannel = "mqttOutputChannel")
public interface MqttGateway {

    void sendMessage(String data, @Header(MqttHeaders.TOPIC) String topic);
}
