package com.ezamora.coffeeshop.application.in;

import java.util.UUID;

import com.ezamora.coffeeshop.domain.model.order.Order;
import com.ezamora.coffeeshop.domain.model.payment.CreditCard;
import com.ezamora.coffeeshop.domain.model.payment.Payment;
import com.ezamora.coffeeshop.domain.model.payment.Receipt;

public interface  OrderingCoffee {

    Order placeOrder(Order order);

    Order updateOrder(UUID orderId, Order order);

    void cancelOrder(UUID orderId);

    Payment payOrder(UUID orderId, CreditCard creditCard);

    Receipt readReceipt(UUID orderId);

    Order takeOrder(UUID orderId);

    Order findOrderById(UUID orderId) ;
}
