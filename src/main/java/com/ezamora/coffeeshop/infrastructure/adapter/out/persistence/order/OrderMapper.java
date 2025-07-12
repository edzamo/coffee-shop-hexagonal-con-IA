package com.ezamora.coffeeshop.infrastructure.adapter.out.persistence.order;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import com.ezamora.coffeeshop.domain.model.enums.Location;
import com.ezamora.coffeeshop.domain.model.enums.Status;
import com.ezamora.coffeeshop.domain.model.order.LineItem;
import com.ezamora.coffeeshop.infrastructure.adapter.out.persistence.order.entity.OrderItem;

import lombok.extern.slf4j.Slf4j;

/**
 * Mapper class to convert between domain Order objects and persistence Order entities.
 * This class is final and has a private constructor as it's a utility class with only static methods.
 */
@Slf4j
public final class OrderMapper {

    private OrderMapper() {
        // Private constructor to prevent instantiation
    }

    public static com.ezamora.coffeeshop.domain.model.order.Order toDomain(com.ezamora.coffeeshop.infrastructure.adapter.out.persistence.order.entity.Order orderEntity) {

        if (orderEntity == null) {
            return null;
        }

        List<LineItem> lineItems = orderEntity.getItems() == null ? Collections.emptyList()
                : orderEntity.getItems().stream()
                        .map(OrderMapper::toDomain)
                        .toList();

        return new com.ezamora.coffeeshop.domain.model.order.Order(
                orderEntity.getUuid(),
                toDomain(orderEntity.getLocation()),
                lineItems,
                toDomain(orderEntity.getStatus())
        );
    }

    public static com.ezamora.coffeeshop.infrastructure.adapter.out.persistence.order.entity.Order toEntity(com.ezamora.coffeeshop.domain.model.order.Order domainOrder) {
        if (domainOrder == null) {
            return null;
        }

        var orderEntity = com.ezamora.coffeeshop.infrastructure.adapter.out.persistence.order.entity.Order.builder()
                .uuid(domainOrder.getId() != null ? domainOrder.getId() : UUID.randomUUID())
                .orderDate(LocalDateTime.now())
                .totalAmount(domainOrder.getCost())
                .location(toEntity(domainOrder.getLocation()))
                .status(toEntity(domainOrder.getStatus()))
                .build();

        List<OrderItem> itemEntities = domainOrder.getItems().stream()
                .map(OrderMapper::toEntity)
                .toList();

        // Use the helper method to establish the bidirectional link correctly
        orderEntity.setItems(itemEntities);

        return orderEntity;
    }

    // Helper methods for LineItem
    private static LineItem toDomain(OrderItem itemEntity) {
        return new LineItem(
                com.ezamora.coffeeshop.domain.model.enums.Drink.valueOf(itemEntity.getDrink().name()),
                com.ezamora.coffeeshop.domain.model.enums.Milk.valueOf(itemEntity.getMilk().name()),
                com.ezamora.coffeeshop.domain.model.enums.Size.valueOf(itemEntity.getSize().name()),
                itemEntity.getQuantity()
        );
    }

    private static OrderItem toEntity(LineItem lineItem) {
        return OrderItem.builder()
                .drink(com.ezamora.coffeeshop.infrastructure.adapter.out.persistence.order.entity.Drink.valueOf(lineItem.drink().name()))
                .milk(com.ezamora.coffeeshop.infrastructure.adapter.out.persistence.order.entity.Milk.valueOf(lineItem.milk().name()))
                .size(com.ezamora.coffeeshop.infrastructure.adapter.out.persistence.order.entity.Size.valueOf(lineItem.size().name()))
                .quantity(lineItem.quantity())
                .build();
    }

    // Helper methods for Enums
    private static Location toDomain(com.ezamora.coffeeshop.infrastructure.adapter.out.persistence.order.entity.OrderLocation locationEntity) {
        return Location.valueOf(locationEntity.name());
    }

    private static com.ezamora.coffeeshop.infrastructure.adapter.out.persistence.order.entity.OrderLocation toEntity(Location domainLocation) {
        return com.ezamora.coffeeshop.infrastructure.adapter.out.persistence.order.entity.OrderLocation.valueOf(domainLocation.name());
    }

    private static Status toDomain(com.ezamora.coffeeshop.infrastructure.adapter.out.persistence.order.entity.OrderStatus statusEntity) {
        return switch (statusEntity) {
            case PENDING_PAYMENT -> Status.PAYMENT_EXPECTED;
            // When loading a completed order, we assume it's paid. The application can then move it to other states.
            case COMPLETED -> Status.PAID;
            // The domain model currently has no 'CANCELLED' state. This would cause an error if loaded.
            case CANCELLED -> throw new IllegalStateException("Domain model does not support CANCELLED status.");
        };
    }

    private static com.ezamora.coffeeshop.infrastructure.adapter.out.persistence.order.entity.OrderStatus toEntity(Status domainStatus) {
        return switch (domainStatus) {
            case PAYMENT_EXPECTED -> com.ezamora.coffeeshop.infrastructure.adapter.out.persistence.order.entity.OrderStatus.PENDING_PAYMENT;
            // All "in-progress" or "finished" states are mapped to COMPLETED in the database.
            case PAID, PREPARING, READY, TAKEN -> com.ezamora.coffeeshop.infrastructure.adapter.out.persistence.order.entity.OrderStatus.COMPLETED;
        };
    }
}


