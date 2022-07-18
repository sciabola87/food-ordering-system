package com.food.ordering.system.service.domain.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "payment-service")
public class PaymentServiceConfigData {

    private String paymentRequestTopicName;
    private String paymentResponseTopicName;
}
