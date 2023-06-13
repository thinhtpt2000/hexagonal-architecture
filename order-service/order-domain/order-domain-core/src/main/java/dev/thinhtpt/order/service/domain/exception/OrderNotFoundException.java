package dev.thinhtpt.order.service.domain.exception;

import dev.thinhtpt.domain.exception.DomainException;

/**
 * The OrderNotFoundException is a class that ...
 *
 * @author Theon Tran Phan Truong Thinh
 * @version 1.0
 * @since 13/06/2023 9:46 AM
 */
public class OrderNotFoundException extends DomainException {
    public OrderNotFoundException(String message) {
        super(message);
    }

    public OrderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
