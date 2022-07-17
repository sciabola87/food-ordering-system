package com.food.ordering.system.kafka.config.data;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "kafka-producer-config")
public class KafkaProducerConfigData {

    private final String keySerializerClass;
    private final String valueSerializerClass;
    private final String compressionType;
    private final String acks;
    private final Integer batchSize;
    private final Integer batchSizeBoostFactor;
    private final Integer lingerMs;
    private final Integer requestTimeoutMs;
    private final Integer retryCount;

}
