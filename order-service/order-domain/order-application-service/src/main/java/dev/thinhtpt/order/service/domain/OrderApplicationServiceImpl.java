package dev.thinhtpt.order.service.domain;

import dev.thinhtpt.order.service.domain.dto.create.CreateOrderCommand;
import dev.thinhtpt.order.service.domain.dto.create.CreateOrderResponse;
import dev.thinhtpt.order.service.domain.dto.track.TrackOrderQuery;
import dev.thinhtpt.order.service.domain.dto.track.TrackOrderResponse;
import dev.thinhtpt.order.service.domain.ports.input.service.OrderApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * The OrderApplicationServiceImpl is a class that ...
 *
 * @author Theon Tran Phan Truong Thinh
 * @version 1.0
 * @since 12/06/2023 10:14 PM
 */
@Slf4j
@Validated
@RequiredArgsConstructor
@Service
public class OrderApplicationServiceImpl implements OrderApplicationService {
    private final OrderCreateCommandHandler orderCreateCommandHandler;
    private final OrderTrackQueryHandler orderTrackQueryHandler;

    @Override
    public CreateOrderResponse createOrder(CreateOrderCommand command) {
        return orderCreateCommandHandler.createOrder(command);
    }

    @Override
    public TrackOrderResponse trackOrder(TrackOrderQuery query) {
        return orderTrackQueryHandler.trackOrder(query);
    }
}
