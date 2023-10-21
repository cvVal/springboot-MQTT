# MQTT Integration

This demo application serves as a practical guide to demonstrate how to seamlessly connect your Spring Boot application to an MQTT broker for message communication.

### Testing Steps
To experience MQTT integration in action, follow these steps:
1. Start a new connection using the Online MQTT broker (link provided below).
2. Add your username and password to the `application.yaml` configuration file.
3. Start the service.
4. Execute a request using the following `curl` command:

```
curl --location 'localhost:8080/v1/notification' \
--header 'Content-Type: application/json' \
--data '{
    "notification": {
        "showWarning": true,
        "alertType": "testing alert"
    }
}'
```

### Useful Links
- [Spring Integration MQTT Documentation](https://docs.spring.io/spring-integration/docs/current/reference/html/mqtt.html): Access comprehensive documentation on Spring Integration MQTT for effective integration.
- [Easy-To-Use Online MQTT Client](http://www.emqx.io/online-mqtt-client): Explore an online MQTT client to gain hands-on experience with MQTT communication.
