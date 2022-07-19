package com.food.ordering.system.estaurant.service.messaging.listener.kafka;

import com.food.ordering.system.estaurant.service.messaging.mapper.RestaurantMassagingDataMapper;
import com.food.ordering.system.kafka.consumer.KafkaConsumer;
import com.food.ordering.system.kafka.order.avro.model.RestaurantApprovalRequestAvroModel;
import com.food.ordering.system.restaurant.service.domain.ports.input.message.listener.RestaurantApprovalRequestMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class RestaurantApprovalRequestKafkaListener implements KafkaConsumer<RestaurantApprovalRequestAvroModel> {


    private final RestaurantApprovalRequestMessageListener restaurantApprovalRequestMessageListener;
    private final RestaurantMassagingDataMapper restaurantMassagingDataMapper;

    public RestaurantApprovalRequestKafkaListener(RestaurantApprovalRequestMessageListener
                                                          restaurantApprovalRequestMessageListener,
                                                  RestaurantMassagingDataMapper
                                                          restaurantMassagingDataMapper) {
        this.restaurantApprovalRequestMessageListener = restaurantApprovalRequestMessageListener;
        this.restaurantMassagingDataMapper = restaurantMassagingDataMapper;
    }

    @Override
    @KafkaListener(id = "${kafka-consumer-config.restaurant-approval-consumer-group-id}",
            topics ="${restaurant-service.restaurant-approval-request-topic-name}")

    public void receive(@Payload List<RestaurantApprovalRequestAvroModel> messages,
                        @Header List<String> keys,
                        @Header List<Integer> partitions,
                        @Header List<Long> offsets) {
        log.info("{} number of orders approval requests received with keys: {}, partitions: {} and offsets: {}",
                messages.size(), keys.toString(), partitions.toString(), offsets.toString());

        messages.forEach(restaurantApprovalRequestAvroModel -> {
            log.info("Processing order approval event id: {}",System.nanoTime());
               restaurantApprovalRequestMessageListener.approveOrder(restaurantMassagingDataMapper
                       .restaurantApprovalRequestAvroModelToRestaurantApproval(restaurantApprovalRequestAvroModel));
        });


    }

//
//    public RestaurantApprovalRequestKafkaListener(RestaurantApprovalRequestMessageListener
//                                                          restaurantApprovalRequestMessageListener,
//                                                  RestaurantMassagingDataMapper
//                                                          restaurantMassagingDataMapper) {
//        this.restaurantApprovalRequestMessageListener = restaurantApprovalRequestMessageListener;
//        this.restaurantMassagingDataMapper = restaurantMassagingDataMapper;
//    }
//
//    @Override
//    @KafkaListener(id = "${kafka-consumer-config.restaurant-approval-consumer-group-id}",
//            topics ="${restaurant-service.restaurant-approval-request-topic-name}")
//    public void receive(Payload ListPayload<PaymentRequestAvroModel> messages,
//                        @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) List<String> keys,
//                        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
//                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
//
//        log.info("{} number of orders approval requests received with keys: {}, partitions: {} and offsets: {}",
//                messages.size(), keys.toString(), partitions.toString(), offsets.toString());
//
//        messages.forEach(restaurantRequestAvroModel -> {
//            log.info("Processing order approval event id: {}",System.nanoTime());
//               restaurantApprovalRequestMessageListener.approveOrder(restaurantMassagingDataMapper
//                       .restaurantApprovalRequestAvroModelToRestaurantApproval(RestaurantApprovalRequestAvroModel));
//        });
//    }
}
