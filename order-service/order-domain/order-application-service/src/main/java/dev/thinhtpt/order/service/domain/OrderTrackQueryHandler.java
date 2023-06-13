package dev.thinhtpt.order.service.domain;

import dev.thinhtpt.order.service.domain.dto.track.TrackOrderQuery;
import dev.thinhtpt.order.service.domain.dto.track.TrackOrderResponse;
import dev.thinhtpt.order.service.domain.entity.Order;
import dev.thinhtpt.order.service.domain.exception.OrderNotFoundException;
import dev.thinhtpt.order.service.domain.mapper.OrderDataMapper;
import dev.thinhtpt.order.service.domain.ports.output.repository.OrderRepository;
import dev.thinhtpt.order.service.domain.valueobject.TrackingId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * The OrderTrackQueryHandler is a class that ...
 *
 * @author Theon Tran Phan Truong Thinh
 * @version 1.0
 * @since 12/06/2023 10:25 PM
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class OrderTrackQueryHandler {
    private final OrderDataMapper orderDataMapper;
    private final OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public TrackOrderResponse trackOrder(TrackOrderQuery query) {
        Optional<Order> orderResult = orderRepository.findByTrackingId(new TrackingId(query.getTrackingId()));
        if (orderResult.isEmpty()) {
            log.warn("Could not find order with tracking id: {}", query.getTrackingId());
            throw new OrderNotFoundException("Could not find order with tracking id: " + query.getTrackingId());
        }
        return orderDataMapper.orderToTrackOrderResponse(orderResult.get());
    }
}
