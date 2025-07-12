package com.ezamora.coffeeshop.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ezamora.coffeeshop.application.in.PreparingCoffee;
import com.ezamora.coffeeshop.application.out.Orders;
import com.ezamora.coffeeshop.domain.model.order.Order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CoffeeMachine implements PreparingCoffee {

    private final Orders orders;

    @Override
    public Order startPreparingOrder(UUID orderId) {
        var order = orders.findOrderById(orderId);
        return orders.save(order.markBeingPrepared());
    }

    @Override
    public Order finishPreparingOrder(UUID orderId) {
        var order = orders.findOrderById(orderId);
        return orders.save(order.markPrepared());
    }

}
