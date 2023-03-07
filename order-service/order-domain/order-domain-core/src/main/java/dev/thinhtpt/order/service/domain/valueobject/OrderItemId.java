package dev.thinhtpt.order.service.domain.valueobject;

import dev.thinhtpt.domain.valueobject.BaseId;

public class OrderItemId extends BaseId<Long> {
    public OrderItemId(Long value) {
        super(value);
    }
}
