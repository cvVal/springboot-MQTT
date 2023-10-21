package com.mqtt.example.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mqtt.example.demo.dtos.MQTTNotificationMessage;
import com.mqtt.example.demo.dtos.Notification;
import com.mqtt.example.demo.services.NotificationService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NotificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private NotificationService notificationService;

    @Test
    void alertNotification() throws Exception {
        // Given
        MQTTNotificationMessage request = MQTTNotificationMessage.builder()
                .notification(
                        Notification.builder()
                                .showWarning(true)
                                .alertType("test notification")
                                .build()
                )
                .build();

        // When
        mockMvc.perform(
                        post("/v1/notification")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

        // Then
        ArgumentCaptor<MQTTNotificationMessage> requestCaptor = ArgumentCaptor.forClass(MQTTNotificationMessage.class);

        then(notificationService)
                .should()
                .sendNotification(requestCaptor.capture());

        MQTTNotificationMessage capturedRequest = requestCaptor.getValue();

        assertEquals(request.getNotification().getShowWarning(), capturedRequest.getNotification().getShowWarning());
        assertEquals(request.getNotification().getAlertType(), capturedRequest.getNotification().getAlertType());
    }
}