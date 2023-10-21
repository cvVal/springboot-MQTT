package com.mqtt.example.demo.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "mqtt")
public class MqttProperties {

    private String brokerAddress;
    private String brokerPort;
    private String topic;
    private String tls;
    private String clientId;
    private String username;
    private String password;
}
