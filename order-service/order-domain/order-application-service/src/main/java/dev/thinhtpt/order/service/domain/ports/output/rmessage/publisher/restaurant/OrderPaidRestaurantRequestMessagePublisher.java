package dev.thinhtpt.order.service.domain.ports.output.rmessage.publisher.restaurant;

import dev.thinhtpt.domain.event.publisher.DomainEventPublisher;
import dev.thinhtpt.order.service.domain.event.OrderPaidEvent;

/**
 * The OrderPaidRestaurantRequestMessagePublisher is a class that ...
 *
 * @author Theon Tran Phan Truong Thinh
 * @version 1.0
 * @since 12/06/2023 7:53 PM
 */
public interface OrderPaidRestaurantRequestMessagePublisher extends DomainEventPublisher<OrderPaidEvent> {
}
