package io.accelerate.solutions.CHK.checkout;

import io.accelerate.solutions.CHK.basket.Basket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TotalPriceCalculatorTest {

    private TotalPriceCalculator totalPriceCalculator;

    @BeforeEach
    void setUp() {
        totalPriceCalculator = new TotalPriceCalculator();
    }

    @Test
    public void shouldCalculateTotalPrice() {
        int totalPrice = totalPriceCalculator.computeTotalPrice(Basket.fromInput("A"));

        assertEquals(50, totalPrice);
    }
}