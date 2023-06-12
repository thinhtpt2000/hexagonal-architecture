package dev.thinhtpt.order.service.domain.ports.output.rmessage.publisher.payment;

import dev.thinhtpt.domain.event.publisher.DomainEventPublisher;
import dev.thinhtpt.order.service.domain.event.OrderCancelledEvent;

/**
 * The OrderCancelledPaymentRequestMessagePublisher is an interface that ...
 *
 * @author Theon Tran Phan Truong Thinh
 * @version 1.0
 * @since 12/06/2023 7:52 PM
 */
public interface OrderCancelledPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCancelledEvent> {
}
