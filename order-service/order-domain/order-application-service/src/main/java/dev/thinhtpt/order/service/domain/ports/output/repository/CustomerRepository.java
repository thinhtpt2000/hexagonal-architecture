package dev.thinhtpt.order.service.domain.ports.output.repository;

import dev.thinhtpt.domain.valueobject.CustomerId;
import dev.thinhtpt.order.service.domain.entity.Customer;

import java.util.Optional;

/**
 * The CustomerRepository is an interface that ...
 *
 * @author Theon Tran Phan Truong Thinh
 * @version 1.0
 * @since 12/06/2023 7:50 PM
 */
public interface CustomerRepository {
    Optional<Customer> findCustomer(CustomerId customerId);
}
