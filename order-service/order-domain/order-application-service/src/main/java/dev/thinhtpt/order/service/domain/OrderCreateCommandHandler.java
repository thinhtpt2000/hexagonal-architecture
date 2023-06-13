package dev.thinhtpt.order.service.domain;

import dev.thinhtpt.order.service.domain.dto.create.CreateOrderCommand;
import dev.thinhtpt.order.service.domain.dto.create.CreateOrderResponse;
import dev.thinhtpt.order.service.domain.event.OrderCreatedEvent;
import dev.thinhtpt.order.service.domain.mapper.OrderDataMapper;
import dev.thinhtpt.order.service.domain.ports.output.rmessage.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * The OrderCreateCommandHandler is a class that ...
 *
 * @author Theon Tran Phan Truong Thinh
 * @version 1.0
 * @since 12/06/2023 10:20 PM
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class OrderCreateCommandHandler {
    private final OrderCreateHelper orderCreateHelper;
    private final OrderDataMapper orderDataMapper;
    private final OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher;

    public CreateOrderResponse createOrder(CreateOrderCommand command) {
        OrderCreatedEvent orderCreatedEvent = orderCreateHelper.persistOrder(command);
        log.info("Order is created with id: {}", orderCreatedEvent.getOrder().getId().getValue());
        orderCreatedPaymentRequestMessagePublisher.publish(orderCreatedEvent);
        return orderDataMapper.orderToCreateOrderResponse(orderCreatedEvent.getOrder(), "Order Created Successfully");
    }

}
