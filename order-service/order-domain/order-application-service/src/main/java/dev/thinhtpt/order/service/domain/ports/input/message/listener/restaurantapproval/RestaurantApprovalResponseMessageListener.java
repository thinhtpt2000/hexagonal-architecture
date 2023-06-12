package dev.thinhtpt.order.service.domain.ports.input.message.listener.restaurantapproval;

import dev.thinhtpt.order.service.domain.dto.message.RestaurantApprovalResponse;

/**
 * The RestaurantApprovalResponseMessageListener is an interface that ...
 *
 * @author Theon Tran Phan Truong Thinh
 * @version 1.0
 * @since 12/06/2023 7:46 PM
 */
public interface RestaurantApprovalResponseMessageListener {
    void orderApproved(RestaurantApprovalResponse response);

    void orderRejected(RestaurantApprovalResponse response);
}
