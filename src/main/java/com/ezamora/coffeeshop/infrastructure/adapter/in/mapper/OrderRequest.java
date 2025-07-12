package com.ezamora.coffeeshop.infrastructure.adapter.in.mapper;

import java.util.List;

import com.ezamora.coffeeshop.domain.model.enums.Location;
import com.ezamora.coffeeshop.domain.model.order.Order;

public record OrderRequest(Location location, List<LineItemRequest> items) {

    public Order toDomain() {
        return new Order(location, items.stream().map(LineItemRequest::toDomain).toList());
    }
}
