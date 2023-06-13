package dev.thinhtpt.order.service.domain.mapper;

import dev.thinhtpt.domain.valueobject.CustomerId;
import dev.thinhtpt.domain.valueobject.Money;
import dev.thinhtpt.domain.valueobject.ProductId;
import dev.thinhtpt.domain.valueobject.RestaurantId;
import dev.thinhtpt.order.service.domain.dto.create.CreateOrderCommand;
import dev.thinhtpt.order.service.domain.dto.create.CreateOrderResponse;
import dev.thinhtpt.order.service.domain.dto.create.OrderAddress;
import dev.thinhtpt.order.service.domain.dto.track.TrackOrderResponse;
import dev.thinhtpt.order.service.domain.entity.Order;
import dev.thinhtpt.order.service.domain.entity.OrderItem;
import dev.thinhtpt.order.service.domain.entity.Product;
import dev.thinhtpt.order.service.domain.entity.Restaurant;
import dev.thinhtpt.order.service.domain.valueobject.StreetAddress;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * The OrderDataMapper is a class that ...
 *
 * @author Theon Tran Phan Truong Thinh
 * @version 1.0
 * @since 12/06/2023 7:40 PM
 */
@Component
public class OrderDataMapper {

    public Restaurant createOrderCommandToRestaurant(CreateOrderCommand command) {
        return Restaurant.builder()
                .restaurantId(new RestaurantId(command.getRestaurantId()))
                .products(command.getItems().stream().map(orderItem
                                -> new Product(new ProductId(orderItem.getProductId())))
                        .toList())
                .build();
    }

    public Order createOrderCommandToOrder(CreateOrderCommand command) {
        return Order.builder()
                .customerId(new CustomerId((command.getCustomerId())))
                .restaurantId(new RestaurantId(command.getRestaurantId()))
                .deliveryAddress(orderAddressToStreetAddress(command.getAddress()))
                .price(new Money(command.getPrice()))
                .items(orderItemsToOrderItemEntities(command.getItems()))
                .build();
    }

    public CreateOrderResponse orderToCreateOrderResponse(Order order, String message) {
        return CreateOrderResponse.builder()
                .trackingId(order.getTrackingId().getValue())
                .orderStatus(order.getOrderStatus())
                .message(message)
                .build();
    }

    public TrackOrderResponse orderToTrackOrderResponse(Order order) {
        return TrackOrderResponse.builder()
                .orderStatus(order.getOrderStatus())
                .trackingId(order.getTrackingId().getValue())
                .failureMessage(order.getFailureMessages())
                .build();
    }

    private List<OrderItem> orderItemsToOrderItemEntities(List<dev.thinhtpt.order.service.domain.dto.create.OrderItem> items) {
        return items.stream().map(
                orderItem -> OrderItem.builder()
                        .product(new Product(new ProductId(orderItem.getProductId())))
                        .price(new Money(orderItem.getPrice()))
                        .quantity(orderItem.getQuantity())
                        .subTotal(new Money(orderItem.getSubTotal()))
                        .build()

        ).toList();
    }

    private StreetAddress orderAddressToStreetAddress(OrderAddress address) {
        return new StreetAddress(
                UUID.randomUUID(),
                address.getStreet(),
                address.getPostalCode(),
                address.getCity()
        );
    }
}
