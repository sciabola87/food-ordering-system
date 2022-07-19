package com.food.ordering.system.restaurant.service.domain.event;

import com.food.ordering.system.domain.event.DomainEvent;
import com.food.ordering.system.domain.valueobject.RestaurantId;
import com.food.ordering.system.restaurant.service.domain.entity.OrderApproval;

import java.time.ZonedDateTime;
import java.util.List;

public abstract class OrderApprovalEvent implements DomainEvent<OrderApproval> {

    private final OrderApproval orderApproval;
    private final RestaurantId restaurantId;
    private final List<String> FailureMessages;
    private final ZonedDateTime createAt;

    public OrderApprovalEvent(OrderApproval orderApproval,
                              RestaurantId restaurantId,
                              List<String> failureMessages,
                              ZonedDateTime createAt) {
        this.orderApproval = orderApproval;
        this.restaurantId = restaurantId;
        FailureMessages = failureMessages;
        this.createAt = createAt;
    }

    public OrderApproval getOrderApproval() {
        return orderApproval;
    }

    public RestaurantId getRestaurantId() {
        return restaurantId;
    }

    public List<String> getFailureMessages() {
        return FailureMessages;
    }

    public ZonedDateTime getCreateAt() {
        return createAt;
    }
}
