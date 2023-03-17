package dev.thinhtpt.order.service.domain.dto.track;

import dev.thinhtpt.domain.valueobject.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class TrackOrderResponse {
    @NotNull
    private final UUID trackingId;
    @NotNull
    private final OrderStatus orderStatus;
    private final List<String> failureMessage;
}
