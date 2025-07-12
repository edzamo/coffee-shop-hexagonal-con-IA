package com.ezamora.coffeeshop.domain.model.order;

import java.math.BigDecimal;

import com.ezamora.coffeeshop.domain.model.enums.Drink;
import com.ezamora.coffeeshop.domain.model.enums.Milk;
import com.ezamora.coffeeshop.domain.model.enums.Size;

public record LineItem(Drink drink, Milk milk, Size size, int quantity) {

    // For simplicity every small drink costs 4.0 and large 5.0
    BigDecimal getCost() {
        var price = BigDecimal.valueOf(4.0);
        if (size == Size.LARGE) {
            price = price.add(BigDecimal.ONE);
        }
        return price.multiply(BigDecimal.valueOf(quantity));
    }
}
