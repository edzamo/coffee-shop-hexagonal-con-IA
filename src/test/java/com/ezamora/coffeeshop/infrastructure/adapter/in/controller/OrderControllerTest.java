package com.ezamora.coffeeshop.infrastructure.adapter.in.controller;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ezamora.coffeeshop.application.in.OrderingCoffee;
import com.ezamora.coffeeshop.domain.model.enums.Drink;
import com.ezamora.coffeeshop.domain.model.enums.Location;
import com.ezamora.coffeeshop.domain.model.enums.Milk;
import com.ezamora.coffeeshop.domain.model.enums.Size;
import com.ezamora.coffeeshop.domain.model.order.LineItem;
import com.ezamora.coffeeshop.domain.model.order.Order;
import com.ezamora.coffeeshop.infrastructure.adapter.in.mapper.LineItemRequest;
import com.ezamora.coffeeshop.infrastructure.adapter.in.mapper.OrderRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderingCoffee orderingCoffee;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createOrder_shouldReturnCreated() throws Exception {
        // Given
        var lineItemRequest = new LineItemRequest(Drink.LATTE, Milk.WHOLE, Size.LARGE, 1);
        var orderRequest = new OrderRequest(Location.TAKE_AWAY, List.of(lineItemRequest));
        UUID orderId = UUID.randomUUID();

        var domainOrder = new Order(
            orderId,
            Location.TAKE_AWAY,
            List.of(new LineItem(Drink.LATTE, Milk.WHOLE, Size.LARGE, 1)), null
        );

        when(orderingCoffee.placeOrder(any(Order.class))).thenReturn(domainOrder);

        // When & Then
        mockMvc.perform(post("/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderRequest)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/order/" + orderId))
                .andExpect(jsonPath("$.location").value("TAKE_AWAY"))
                .andExpect(jsonPath("$.items[0].drink").value("LATTE"));

        verify(orderingCoffee).placeOrder(any(Order.class));
    }

}