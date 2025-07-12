package com.ezamora.coffeeshop.application.out;

import java.util.UUID;

import com.ezamora.coffeeshop.domain.model.order.Order;

public interface Orders {

    Order findOrderById(UUID orderId) throws OrderNotFound;

    Order save(Order order);

    void deleteById(UUID orderId);

}
