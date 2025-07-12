package com.ezamora.coffeeshop.application.service;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ezamora.coffeeshop.application.out.Orders;
import com.ezamora.coffeeshop.application.out.Payments;
import com.ezamora.coffeeshop.domain.model.enums.Drink;
import com.ezamora.coffeeshop.domain.model.enums.Location;
import com.ezamora.coffeeshop.domain.model.enums.Milk;
import com.ezamora.coffeeshop.domain.model.enums.Size;
import com.ezamora.coffeeshop.domain.model.order.LineItem;
import com.ezamora.coffeeshop.domain.model.order.Order;

@ExtendWith(MockitoExtension.class)
class CoffeeShopTest {

    @Mock
    private Orders orders;

    @Mock
    private Payments payments;

    @InjectMocks
    private CoffeeShop coffeeShop;

    @Test
    void placeOrder_shouldSaveOrder() {
        // Given
        var order = new Order(Location.TAKE_AWAY, List.of(new LineItem(Drink.LATTE, Milk.WHOLE, Size.LARGE, 1)));
        when(orders.save(order)).thenReturn(order);

        // When
        var placedOrder = coffeeShop.placeOrder(order);

        // Then
        assertThat(placedOrder).isEqualTo(order);
        verify(orders).save(order);
    }


    @Test
    void findOrderById_shouldReturnOrder() {
        // Given
        var orderId = UUID.randomUUID();
        var expectedOrder = new Order(orderId, Location.TAKE_AWAY, List.of(), null);
        when(orders.findOrderById(orderId)).thenReturn(expectedOrder);

        // When
        var foundOrder = coffeeShop.findOrderById(orderId);

        // Then
        assertThat(foundOrder).isEqualTo(expectedOrder);
        verify(orders).findOrderById(orderId);
    }

}