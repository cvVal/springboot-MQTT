package com.mqtt.example.demo.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mqtt.example.demo.config.MqttProperties;
import com.mqtt.example.demo.dtos.MQTTNotificationMessage;
import com.mqtt.example.demo.dtos.Notification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class NotificationServiceImplTest {

    @Captor
    private ArgumentCaptor<MQTTNotificationMessage> mqttNotificationMessageArgumentCaptor;

    @Captor
    private ArgumentCaptor<String> mqttMessageArgumentCaptor;

    @Captor
    private ArgumentCaptor<String> topicArgumentCaptor;

    @Mock
    private MqttGateway mqttGatewayMock;

    @Mock
    private ObjectMapper objectMapperMock;

    @Mock
    private MqttProperties mqttPropertiesMock;

    @InjectMocks
    private NotificationServiceImpl notificationServiceMock;

    @Test
    void sendNotification() throws JsonProcessingException {
        MQTTNotificationMessage request = MQTTNotificationMessage.builder()
                .notification(
                        Notification.builder()
                                .showWarning(true)
                                .alertType("test notification")
                                .build()
                )
                .build();
        String mqttMessage = objectMapperMock.writeValueAsString(request);
        String topic = "/mock/topic";

        given(mqttPropertiesMock.getTopic())
                .willReturn(topic);

        notificationServiceMock.sendNotification(request);

        then(mqttGatewayMock)
                .should()
                .sendMessage(mqttMessageArgumentCaptor.capture(), topicArgumentCaptor.capture());

        String capturedMqttMessage = mqttMessageArgumentCaptor.getValue();
        String capturedTopic = topicArgumentCaptor.getValue();

        assertEquals(mqttMessage, capturedMqttMessage);
        assertEquals(topic, capturedTopic);
    }
}