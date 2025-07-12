package com.ezamora.coffeeshop.infrastructure.adapter.in.mapper;

import com.ezamora.coffeeshop.domain.model.enums.Drink;
import com.ezamora.coffeeshop.domain.model.enums.Milk;
import com.ezamora.coffeeshop.domain.model.enums.Size;
import com.ezamora.coffeeshop.domain.model.order.LineItem;

public record LineItemResponse(Drink drink, Milk milk, Size size, Integer quantity) {

    public static LineItemResponse fromDomain(LineItem lineItem) {

        return new LineItemResponse(
                lineItem.drink(),
                lineItem.milk(),
                lineItem.size(),
                lineItem.quantity()
        );
    }
}
