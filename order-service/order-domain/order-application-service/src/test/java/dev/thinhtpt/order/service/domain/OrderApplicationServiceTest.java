package dev.thinhtpt.order.service.domain;

import dev.thinhtpt.domain.valueobject.CustomerId;
import dev.thinhtpt.domain.valueobject.Money;
import dev.thinhtpt.domain.valueobject.OrderId;
import dev.thinhtpt.domain.valueobject.OrderStatus;
import dev.thinhtpt.domain.valueobject.ProductId;
import dev.thinhtpt.domain.valueobject.RestaurantId;
import dev.thinhtpt.order.service.domain.dto.create.CreateOrderCommand;
import dev.thinhtpt.order.service.domain.dto.create.CreateOrderResponse;
import dev.thinhtpt.order.service.domain.dto.create.OrderAddress;
import dev.thinhtpt.order.service.domain.dto.create.OrderItem;
import dev.thinhtpt.order.service.domain.entity.Customer;
import dev.thinhtpt.order.service.domain.entity.Order;
import dev.thinhtpt.order.service.domain.entity.Product;
import dev.thinhtpt.order.service.domain.entity.Restaurant;
import dev.thinhtpt.order.service.domain.exception.OrderDomainException;
import dev.thinhtpt.order.service.domain.mapper.OrderDataMapper;
import dev.thinhtpt.order.service.domain.ports.input.service.OrderApplicationService;
import dev.thinhtpt.order.service.domain.ports.output.repository.CustomerRepository;
import dev.thinhtpt.order.service.domain.ports.output.repository.OrderRepository;
import dev.thinhtpt.order.service.domain.ports.output.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * The OrderApplicationServiceTest is a class that ...
 *
 * @author Theon Tran Phan Truong Thinh
 * @version 1.0
 * @since 13/06/2023 10:32 AM
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = OrderTestConfiguration.class)
public class OrderApplicationServiceTest {
    @Autowired
    private OrderApplicationService orderApplicationService;
    @Autowired
    private OrderDataMapper orderDataMapper;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;

    private CreateOrderCommand createOrderCommand;
    private CreateOrderCommand createOrderCommandWrongPrice;
    private CreateOrderCommand createOrderCommandWrongProductPrice;
    private CreateOrderCommand createOrderCommandInactiveRestaurant;
    private final UUID CUSTOMER_ID = UUID.fromString("e6d8d8f0-2cbd-4d7b-9f6d-32e72dfc9a36");
    private final UUID RESTAURANT_ACTIVE_ID = UUID.fromString("b7e72b58-c589-4491-819c-fab5ec35a99c");
    private final UUID RESTAURANT_INACTIVE_ID = UUID.fromString("65ee3a7e-5bbe-43cb-9e08-aab92ffd5662");
    private final UUID PRODUCT_ID = UUID.fromString("f4347672-0827-48ff-af00-d785f7c93218");
    private final UUID ORDER_ID = UUID.fromString("ab96698b-98da-4a8d-8b50-1a977576b333");
    private final BigDecimal PRICE = new BigDecimal("200.00");

    @BeforeAll
    public void init() {
        createOrderCommand = CreateOrderCommand.builder()
                .customerId(CUSTOMER_ID)
                .restaurantId(RESTAURANT_ACTIVE_ID)
                .address(OrderAddress.builder()
                        .street("street_1")
                        .postalCode("700000")
                        .city("Ho Chi Minh")
                        .build())
                .price(PRICE)
                .items(List.of(
                        OrderItem.builder()
                                .productId(PRODUCT_ID)
                                .quantity(1)
                                .price(new BigDecimal("50.00"))
                                .subTotal(new BigDecimal("50.00"))
                                .build(),
                        OrderItem.builder()
                                .productId(PRODUCT_ID)
                                .quantity(3)
                                .price(new BigDecimal("50.00"))
                                .subTotal(new BigDecimal("150.00"))
                                .build()
                ))
                .build();
        createOrderCommandWrongPrice = CreateOrderCommand.builder()
                .customerId(CUSTOMER_ID)
                .restaurantId(RESTAURANT_ACTIVE_ID)
                .address(OrderAddress.builder()
                        .street("street_1")
                        .postalCode("700000")
                        .city("Ho Chi Minh")
                        .build())
                .price(new BigDecimal("250.00"))
                .items(List.of(
                        OrderItem.builder()
                                .productId(PRODUCT_ID)
                                .quantity(1)
                                .price(new BigDecimal("50.00"))
                                .subTotal(new BigDecimal("50.00"))
                                .build(),
                        OrderItem.builder()
                                .productId(PRODUCT_ID)
                                .quantity(3)
                                .price(new BigDecimal("50.00"))
                                .subTotal(new BigDecimal("150.00"))
                                .build()
                ))
                .build();
        createOrderCommandWrongProductPrice = CreateOrderCommand.builder()
                .customerId(CUSTOMER_ID)
                .restaurantId(RESTAURANT_ACTIVE_ID)
                .address(OrderAddress.builder()
                        .street("street_1")
                        .postalCode("700000")
                        .city("Ho Chi Minh")
                        .build())
                .price(new BigDecimal("210.00"))
                .items(List.of(
                        OrderItem.builder()
                                .productId(PRODUCT_ID)
                                .quantity(1)
                                .price(new BigDecimal("60.00"))
                                .subTotal(new BigDecimal("60.00"))
                                .build(),
                        OrderItem.builder()
                                .productId(PRODUCT_ID)
                                .quantity(3)
                                .price(new BigDecimal("50.00"))
                                .subTotal(new BigDecimal("150.00"))
                                .build()
                ))
                .build();
        createOrderCommandInactiveRestaurant = CreateOrderCommand.builder()
                .customerId(CUSTOMER_ID)
                .restaurantId(RESTAURANT_INACTIVE_ID)
                .address(OrderAddress.builder()
                        .street("street_1")
                        .postalCode("700000")
                        .city("Ho Chi Minh")
                        .build())
                .price(PRICE)
                .items(List.of(
                        OrderItem.builder()
                                .productId(PRODUCT_ID)
                                .quantity(1)
                                .price(new BigDecimal("50.00"))
                                .subTotal(new BigDecimal("50.00"))
                                .build(),
                        OrderItem.builder()
                                .productId(PRODUCT_ID)
                                .quantity(3)
                                .price(new BigDecimal("50.00"))
                                .subTotal(new BigDecimal("150.00"))
                                .build()
                ))
                .build();

        Customer customer = new Customer();
        customer.setId(new CustomerId(CUSTOMER_ID));

        Restaurant restaurantResponse = Restaurant.builder()
                .restaurantId(new RestaurantId(RESTAURANT_ACTIVE_ID))
                .active(true)
                .products(List.of(
                                new Product(
                                        new ProductId(PRODUCT_ID),
                                        "product-1",
                                        new Money(new BigDecimal("50.00"))),
                                new Product(
                                        new ProductId(PRODUCT_ID),
                                        "product-2",
                                        new Money(new BigDecimal("50.00")))
                        )
                )
                .build();

        Order order = orderDataMapper.createOrderCommandToOrder(createOrderCommand);
        order.setId(new OrderId(ORDER_ID));

        when(customerRepository.findCustomer(any())).thenReturn(Optional.of(customer));
        when(restaurantRepository.findRestaurantInformation(any()))
                .thenReturn(Optional.of(restaurantResponse));
        when(orderRepository.save(any())).thenReturn(order);
    }

    @Test
    public void createOrder_validCommand_createdSuccessfully() {
        CreateOrderResponse createOrderResponse = orderApplicationService.createOrder(createOrderCommand);
        assertEquals(OrderStatus.PENDING, createOrderResponse.getOrderStatus());
        assertEquals("Order Created Successfully", createOrderResponse.getMessage());
        assertNotNull(createOrderResponse.getTrackingId());
    }

    @Test
    public void createOrder_withWrongTotalPrice_throwException() {
        OrderDomainException orderDomainException = assertThrows(OrderDomainException.class,
                () -> orderApplicationService.createOrder(createOrderCommandWrongPrice));
        assertEquals("Total price: 250.00 is not equal to order items total: 200.00", orderDomainException.getMessage());
    }

    @Test
    public void createOrder_withWrongProductPrice_throwException() {
        OrderDomainException orderDomainException = assertThrows(OrderDomainException.class,
                () -> orderApplicationService.createOrder(createOrderCommandWrongProductPrice));
        assertEquals("Order item price: 60.00 is not valid for product " + PRODUCT_ID, orderDomainException.getMessage());
    }

    @Test
    public void createOrder_withInactiveRestaurant_throwException() {
        Restaurant restaurantResponse = Restaurant.builder()
                .restaurantId(new RestaurantId(RESTAURANT_INACTIVE_ID))
                .active(false)
                .products(List.of(
                                new Product(
                                        new ProductId(PRODUCT_ID),
                                        "product-1",
                                        new Money(new BigDecimal("50.00"))),
                                new Product(
                                        new ProductId(PRODUCT_ID),
                                        "product-2",
                                        new Money(new BigDecimal("50.00")))
                        )
                )
                .build();
        when(restaurantRepository.findRestaurantInformation(orderDataMapper.createOrderCommandToRestaurant(createOrderCommandInactiveRestaurant)))
                .thenReturn(Optional.of(restaurantResponse));

        OrderDomainException orderDomainException = assertThrows(OrderDomainException.class,
                () -> orderApplicationService.createOrder(createOrderCommandInactiveRestaurant));
        assertEquals("Restaurant with id " + RESTAURANT_INACTIVE_ID + " is currently not active!", orderDomainException.getMessage());
    }
}
