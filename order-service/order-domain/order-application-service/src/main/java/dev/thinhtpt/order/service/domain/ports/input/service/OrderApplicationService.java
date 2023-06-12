package dev.thinhtpt.order.service.domain.ports.input.service;

import dev.thinhtpt.order.service.domain.dto.create.CreateOrderCommand;
import dev.thinhtpt.order.service.domain.dto.create.CreateOrderResponse;
import dev.thinhtpt.order.service.domain.dto.track.TrackOrderQuery;
import dev.thinhtpt.order.service.domain.dto.track.TrackOrderResponse;
import jakarta.validation.Valid;

/**
 * The OrderApplicationService is an interface that ...
 *
 * @author Theon Tran Phan Truong Thinh
 * @version 1.0
 * @since 12/06/2023 7:42 PM
 */
public interface OrderApplicationService {
    CreateOrderResponse createOrder(@Valid CreateOrderCommand command);

    TrackOrderResponse trackOrder(@Valid TrackOrderQuery query);
}
