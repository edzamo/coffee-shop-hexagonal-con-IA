package com.ezamora.coffeeshop.application.in;

import java.util.UUID;

import com.ezamora.coffeeshop.domain.model.order.Order;

public interface  PreparingCoffee {

    Order startPreparingOrder(UUID orderId);

    Order finishPreparingOrder(UUID orderId);

}
