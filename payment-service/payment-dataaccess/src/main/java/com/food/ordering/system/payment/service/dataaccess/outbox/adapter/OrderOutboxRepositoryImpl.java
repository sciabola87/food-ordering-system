package com.food.ordering.system.payment.service.dataaccess.outbox.adapter;

import com.food.ordering.system.domain.valueobject.PaymentStatus;
import com.food.ordering.system.outbox.OutboxStatus;
import com.food.ordering.system.payment.service.dataaccess.outbox.exception.OrderOutboxNotFoundException;
import com.food.ordering.system.payment.service.dataaccess.outbox.mapper.OrderOutboxDataAccessMapper;
import com.food.ordering.system.payment.service.dataaccess.outbox.repository.OrderOutboxJpaRepository;
import com.food.ordering.system.service.domain.outbox.model.OrderOutboxMessage;
import com.food.ordering.system.service.domain.ports.output.repository.OrderOutboxRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderOutboxRepositoryImpl implements OrderOutboxRepository {
    private final OrderOutboxJpaRepository orderOutboxJpaRepository;

    private final OrderOutboxDataAccessMapper orderOutboxDataAccessMapper;

    public OrderOutboxRepositoryImpl(OrderOutboxJpaRepository orderOutboxJpaRepository,
                                     OrderOutboxDataAccessMapper orderOutboxDataAccessMapper) {
        this.orderOutboxJpaRepository = orderOutboxJpaRepository;
        this.orderOutboxDataAccessMapper = orderOutboxDataAccessMapper;
    }

    @Override
    public OrderOutboxMessage save(OrderOutboxMessage orderOutboxMessage) {
        return orderOutboxDataAccessMapper.orderOutboxEntityToOrderOutboxMessage(
                orderOutboxJpaRepository
                        .save(orderOutboxDataAccessMapper
                                .orderOutboxMessageToOutboxEntity(orderOutboxMessage)));
    }

    @Override
    public Optional<List<OrderOutboxMessage>> findByTypeAndOutboxStatus(String sagaType, OutboxStatus outboxStatus) {

        return Optional.of(orderOutboxJpaRepository.findByTypeAndOutboxStatus(sagaType,   outboxStatus)
                .orElseThrow(() -> new OrderOutboxNotFoundException("PAyment outbox object" +
                        "could not be found for saga type" +sagaType))
                .stream()
                .map(orderOutboxDataAccessMapper::orderOutboxEntityToOrderOutboxMessage)
                .collect(Collectors.toList()));
    }

    @Override
    public Optional<OrderOutboxMessage> findByTypeAndSagaIdAndPaymentStatusAndOutboxStatus(String sagaType, UUID sagaId,
                                                                                           PaymentStatus paymentStatus, OutboxStatus outboxStatus) {
        return orderOutboxJpaRepository.findByTypeAndSagaIdAndPaymentStatusAndOutboxStatus(sagaType,
                            sagaId, paymentStatus, outboxStatus)
                    .map(orderOutboxDataAccessMapper::orderOutboxEntityToOrderOutboxMessage);
    }




    @Override
    public void deleteByTpeAndOutboxStatus(String type, OutboxStatus outboxStatus) {
        orderOutboxJpaRepository
                .deleteByTypeAndOutboxStatus(type, outboxStatus);
    }
}
