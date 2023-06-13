package dev.thinhtpt.order.service.domain;

import dev.thinhtpt.order.service.domain.ports.output.repository.CustomerRepository;
import dev.thinhtpt.order.service.domain.ports.output.repository.OrderRepository;
import dev.thinhtpt.order.service.domain.ports.output.repository.RestaurantRepository;
import dev.thinhtpt.order.service.domain.ports.output.rmessage.publisher.payment.OrderCancelledPaymentRequestMessagePublisher;
import dev.thinhtpt.order.service.domain.ports.output.rmessage.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;
import dev.thinhtpt.order.service.domain.ports.output.rmessage.publisher.restaurant.OrderPaidRestaurantRequestMessagePublisher;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * The OrderTestConfiguration is a class that ...
 *
 * @author Theon Tran Phan Truong Thinh
 * @version 1.0
 * @since 13/06/2023 10:28 AM
 */
@SpringBootApplication(scanBasePackages = "dev.thinhtpt")
public class OrderTestConfiguration {
    @Bean
    public OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher() {
        return Mockito.mock(OrderCreatedPaymentRequestMessagePublisher.class);
    }

    @Bean
    public OrderCancelledPaymentRequestMessagePublisher orderCancelledPaymentRequestMessagePublisher() {
        return Mockito.mock(OrderCancelledPaymentRequestMessagePublisher.class);
    }

    @Bean
    public OrderPaidRestaurantRequestMessagePublisher orderPaidRestaurantRequestMessagePublisher() {
        return Mockito.mock(OrderPaidRestaurantRequestMessagePublisher.class);
    }

    @Bean
    public OrderRepository orderRepository() {
        return Mockito.mock(OrderRepository.class);
    }

    @Bean
    public RestaurantRepository restaurantRepository() {
        return Mockito.mock(RestaurantRepository.class);
    }

    @Bean
    public CustomerRepository customerRepository() {
        return Mockito.mock(CustomerRepository.class);
    }

    @Bean
    public OrderDomainService orderDomainService() {
        return new OrderDomainServiceImpl();
    }
}
