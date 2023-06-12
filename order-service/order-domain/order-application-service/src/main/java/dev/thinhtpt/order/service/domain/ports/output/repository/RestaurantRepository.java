package dev.thinhtpt.order.service.domain.ports.output.repository;

import dev.thinhtpt.order.service.domain.entity.Restaurant;

import java.util.Optional;

/**
 * The RestaurantRepository is an interface that ...
 *
 * @author Theon Tran Phan Truong Thinh
 * @version 1.0
 * @since 12/06/2023 7:49 PM
 */
public interface RestaurantRepository {
    Optional<Restaurant> findRestaurantInformation(Restaurant restaurant);
}
