package com.mqtt.example.demo.services;

import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class MqttGatewayImpl implements MqttGateway {

    private final MessageHandler mqttOutbound;

    public MqttGatewayImpl(MessageHandler mqttOutbound) {
        this.mqttOutbound = mqttOutbound;
    }

    @Override
    public void sendMessage(String data, String topic) {
        Message<String> message =
                MessageBuilder
                        .withPayload(data)
                        .setHeader(MqttHeaders.TOPIC, topic)
                        .build();

        mqttOutbound.handleMessage(message);
    }
}
