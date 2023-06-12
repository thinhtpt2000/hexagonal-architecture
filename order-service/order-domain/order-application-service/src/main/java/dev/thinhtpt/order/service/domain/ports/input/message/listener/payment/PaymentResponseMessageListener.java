package dev.thinhtpt.order.service.domain.ports.input.message.listener.payment;

import dev.thinhtpt.order.service.domain.dto.message.PaymentResponse;

/**
 * The PaymetResponseMessageListener is an interface that ...
 *
 * @author Theon Tran Phan Truong Thinh
 * @version 1.0
 * @since 12/06/2023 7:45 PM
 */
public interface PaymentResponseMessageListener {
    void paymentCompleted(PaymentResponse response);

    void paymentCancelled(PaymentResponse response);
}
