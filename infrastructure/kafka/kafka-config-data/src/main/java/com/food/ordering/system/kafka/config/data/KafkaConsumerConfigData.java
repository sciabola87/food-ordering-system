package com.food.ordering.system.kafka.config.data;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "kafka-consumer-config")
public class KafkaConsumerConfigData {

    private final String keyDeserializer;
    private final String valueDeserializer;
    private final String autoOffsetReset;
    private final String specificAvroReaderKey;
    private final String specificAvroReader;
    private final Boolean batchListener;
    private final Boolean autoStartup;
    private final Integer concurrencyLevel;
    private final Integer sessionTimeoutMs;
    private final Integer heartbeatIntervalMs;
    private final Integer maxPollIntervalMs;
    private final Long pollTimeoutMs;
    private final Integer maxPollRecords;
    private final Integer maxPartitionFetchBytesDefault;
    private final Integer maxPartitionFetchBytesBoostFactor;


}
