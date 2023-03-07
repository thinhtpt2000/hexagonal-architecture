package dev.thinhtpt.order.service.domain;

import dev.thinhtpt.order.service.domain.entity.Order;
import dev.thinhtpt.order.service.domain.entity.Restaurant;
import dev.thinhtpt.order.service.domain.event.OrderCancelledEvent;
import dev.thinhtpt.order.service.domain.event.OrderCreatedEvent;
import dev.thinhtpt.order.service.domain.event.OrderPaidEvent;

import java.util.List;

public interface OrderDomainService {
    OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant);

    OrderPaidEvent payOrder(Order order);

    void approvedOrder(Order order);

    OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages);

    void cancelOrder(Order order, List<String> failureMessages);
}
