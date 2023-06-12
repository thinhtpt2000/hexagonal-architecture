package dev.thinhtpt.order.service.domain;

import dev.thinhtpt.domain.valueobject.CustomerId;
import dev.thinhtpt.order.service.domain.dto.create.CreateOrderCommand;
import dev.thinhtpt.order.service.domain.entity.Customer;
import dev.thinhtpt.order.service.domain.entity.Order;
import dev.thinhtpt.order.service.domain.entity.Restaurant;
import dev.thinhtpt.order.service.domain.event.OrderCreatedEvent;
import dev.thinhtpt.order.service.domain.exception.OrderDomainException;
import dev.thinhtpt.order.service.domain.mapper.OrderDataMapper;
import dev.thinhtpt.order.service.domain.ports.output.repository.CustomerRepository;
import dev.thinhtpt.order.service.domain.ports.output.repository.OrderRepository;
import dev.thinhtpt.order.service.domain.ports.output.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * The OrderCreateHepler is a class that ...
 *
 * @author Theon Tran Phan Truong Thinh
 * @version 1.0
 * @since 12/06/2023 10:48 PM
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class OrderCreateHelper {
    private final OrderDomainService orderDomainService;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final RestaurantRepository restaurantRepository;
    private final OrderDataMapper orderDataMapper;

    @Transactional
    public OrderCreatedEvent persistOrder(CreateOrderCommand command) {
        checkCustomer(command.getCustomerId());
        Restaurant restaurant = checkRestaurant(command);
        Order order = orderDataMapper.createOrderCommandToOrder(command);
        OrderCreatedEvent orderCreatedEvent = orderDomainService.validateAndInitiateOrder(order, restaurant);
        Order orderResult = saveOrder(order);
        log.info("Order is created with id: {}", orderResult.getId().getValue());
        return orderCreatedEvent;
    }

    private Restaurant checkRestaurant(CreateOrderCommand command) {
        Restaurant restaurant = orderDataMapper.createOrderCommandToRestaurant(command);
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findRestaurantInformation(restaurant);
        if (optionalRestaurant.isEmpty()) {
            log.warn("Could not find the restaurant with restaurant id: {}", command.getRestaurantId());
            throw new OrderDomainException("Could not find the customer with restaurant id: "
                    + command.getRestaurantId());
        }
        return optionalRestaurant.get();
    }

    private void checkCustomer(UUID customerId) {
        Optional<Customer> customer = customerRepository.findCustomer(new CustomerId(customerId));
        if (customer.isEmpty()) {
            log.warn("Could not find the customer with customer id: {}", customerId);
            throw new OrderDomainException("Could not find the customer with customer id: " + customerId);
        }
    }

    private Order saveOrder(Order order) {
        Order orderResult = orderRepository.save(order);
        if (Objects.isNull(orderResult)) {
            log.error("Could not save order!");
            throw new OrderDomainException("Could not save order!");
        }
        log.info("Order is saved with id: {}", orderResult.getId().getValue());
        return orderResult;
    }
}
