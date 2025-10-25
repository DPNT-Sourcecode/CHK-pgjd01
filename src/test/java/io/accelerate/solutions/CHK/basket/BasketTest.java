package io.accelerate.solutions.CHK.basket;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BasketTest {

    private Basket basket;

    @Test
    public void shouldComputeBasketFromInput() {
        basket = Basket.fromInput("4A, 3B, 2C, 1D");

        assertNotNull(basket.getItems());
        assertEquals(basket.getItems().size(), 4);
    }
}
