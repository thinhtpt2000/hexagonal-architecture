package dev.thinhtpt.order.service.domain.ports.output.repository;

import dev.thinhtpt.order.service.domain.entity.Order;
import dev.thinhtpt.order.service.domain.valueobject.TrackingId;

import java.util.Optional;

/**
 * The OrderRepository is an interface that ...
 *
 * @author Theon Tran Phan Truong Thinh
 * @version 1.0
 * @since 12/06/2023 7:47 PM
 */
public interface OrderRepository {
    Order save(Order order);

    Optional<Order> findByTrackingId(TrackingId trackingId);
}
