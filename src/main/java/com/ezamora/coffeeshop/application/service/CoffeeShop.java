package com.ezamora.coffeeshop.application.service;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ezamora.coffeeshop.application.in.OrderingCoffee;
import com.ezamora.coffeeshop.application.out.Orders;
import com.ezamora.coffeeshop.application.out.Payments;
import com.ezamora.coffeeshop.domain.model.order.Order;
import com.ezamora.coffeeshop.domain.model.payment.CreditCard;
import com.ezamora.coffeeshop.domain.model.payment.Payment;
import com.ezamora.coffeeshop.domain.model.payment.Receipt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CoffeeShop implements OrderingCoffee {

    private final Orders orders;
    private final Payments payments;

    @Override
    public Order placeOrder(Order order) {
        return orders.save(order);
    }

    @Override
    public Order updateOrder(UUID orderId, Order order) {
        var orderExists = orders.findOrderById(orderId);
        return orders.save(orderExists.update(order));
    }

    @Override
    public void cancelOrder(UUID orderId) {
        var order = orders.findOrderById(orderId);

        if (!order.canBeCancelled()) {
            throw new IllegalStateException("Order is already paid");
        }

        orders.deleteById(orderId);
    }

    @Override
    public Payment payOrder(UUID orderId, CreditCard creditCard) {
        var order = orders.findOrderById(orderId);

        orders.save(order.markPaid());

        return payments.save(new Payment(orderId, creditCard, LocalDate.now()));
    }

    @Override
    public Receipt readReceipt(UUID orderId) {
        var order = orders.findOrderById(orderId);
        var payment = payments.findPaymentByOrderId(orderId);

        return new Receipt(order.getCost(), payment.paid());
    }

    @Override
    public Order takeOrder(UUID orderId) {
        var order = orders.findOrderById(orderId);

        return orders.save(order.markTaken());
    }


    @Override
    public Order findOrderById(UUID orderId) {
        log.info("Finding service order with ID: {}", orderId);
        var order = orders.findOrderById(orderId);

        return order;
    }

}