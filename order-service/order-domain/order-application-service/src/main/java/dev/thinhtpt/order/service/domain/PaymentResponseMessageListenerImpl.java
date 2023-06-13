package dev.thinhtpt.order.service.domain;

import dev.thinhtpt.order.service.domain.dto.message.PaymentResponse;
import dev.thinhtpt.order.service.domain.ports.input.message.listener.payment.PaymentResponseMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * The PaymentResponseMessageListenerImpl is a class that ...
 *
 * @author Theon Tran Phan Truong Thinh
 * @version 1.0
 * @since 13/06/2023 9:49 AM
 */
@Slf4j
@Validated
@Service
public class PaymentResponseMessageListenerImpl implements PaymentResponseMessageListener {
    @Override
    public void paymentCompleted(PaymentResponse response) {

    }

    @Override
    public void paymentCancelled(PaymentResponse response) {

    }
}
