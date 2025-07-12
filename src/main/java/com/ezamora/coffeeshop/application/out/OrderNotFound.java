package com.ezamora.coffeeshop.application.out;

public class OrderNotFound extends RuntimeException {

    public OrderNotFound(String message) {
        super(message);
    }
}
