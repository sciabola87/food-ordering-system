package com.food.ordering.system.order.service;

import com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.order.service.domain.dto.track.TrackOrderQuery;
import com.food.ordering.system.order.service.domain.ports.input.service.OrderApplicationService;

public class OrderApplicationServiceImpl implements OrderApplicationService {
    @Override
    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
        return null;
    }

    @Override
    public TrackOrderQuery trackOrder(TrackOrderQuery trackOrderQuery) {
        return null;
    }
}
