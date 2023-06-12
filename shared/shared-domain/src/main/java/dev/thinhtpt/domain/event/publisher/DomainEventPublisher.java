package dev.thinhtpt.domain.event.publisher;

import dev.thinhtpt.domain.event.DomainEvent;

/**
 * The DomainEventPublisher is an interface that ...
 *
 * @author Theon Tran Phan Truong Thinh
 * @version 1.0
 * @since 12/06/2023 7:51 PM
 */
public interface DomainEventPublisher<T extends DomainEvent> {
    void publish(T domainEvent);
}
