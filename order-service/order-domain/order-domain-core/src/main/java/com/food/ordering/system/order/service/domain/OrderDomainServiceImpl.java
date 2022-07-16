package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.entity.Product;
import com.food.ordering.system.order.service.domain.entity.Restaurant;
import com.food.ordering.system.order.service.domain.event.OrderCancelledEvent;
import com.food.ordering.system.order.service.domain.event.OrderCreatedEvent;
import com.food.ordering.system.order.service.domain.event.OrderPaidEvent;
import com.food.ordering.system.order.service.domain.exception.OrderDomainException;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Slf4j
public class OrderDomainServiceImpl implements OrderDomainService {

    private static final String UTC = "UTC";

    @Override
    public OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant) {

        validateRestaurant(restaurant);
        setOrderProductInformation(order, restaurant);
        order.validateOrder();
        order.initializeOrder();
        log.info("Order with id: {}", order.getId().getValue());
        return new OrderCreatedEvent(order, ZonedDateTime.now(ZoneId.of(UTC)));
    }


    @Override
    public OrderPaidEvent payOrder(Order order) {
        order.pay();
        log.info("Order with id: {} is paid", order.getId().getValue());
        return new OrderPaidEvent(order, ZonedDateTime.now(ZoneId.of(UTC)) );
    }

    @Override
    public void approveOrder(Order order) {
        order.approve();
        log.info("Order with id: {} is appoved", order.getId().getValue());
    }

    @Override
    public OrderCancelledEvent cancellOrderPayment(Order order, List<String> failureMessages) {
        order.initCancell(failureMessages);
        log.info("Order payment is cancelling for order id: {}", order.getId().getValue());
        return new OrderCancelledEvent(order, ZonedDateTime.now(ZoneId.of(UTC)));
    }

    @Override
    public void cancelledOrder(Order order, List<String> failureMessages) {
        order.cancell(failureMessages);
        log.info("Order with id: {} is cancelled", order.getId().getValue());
    }

    private void validateRestaurant(Restaurant restaurant) {
        if (!restaurant.isActive()){
            throw new OrderDomainException("Resturant with id "+ restaurant.getId().getValue() +
                    " is currently not active!");
        }
    }


    private void setOrderProductInformation(Order order, Restaurant restaurant) {

        order.getItems().forEach(orderItem -> restaurant.getProducts().forEach(resturantProduct ->{
            Product currentProduct = orderItem.getProduct();
            if (currentProduct.equals(resturantProduct)){
                currentProduct.updateWithConfirmedNameAndPrice(resturantProduct.getName(), resturantProduct.getPrice());
            }
        } ));
    }


}