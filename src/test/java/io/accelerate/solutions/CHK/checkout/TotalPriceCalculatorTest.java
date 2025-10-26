package io.accelerate.solutions.CHK.checkout;

import io.accelerate.solutions.CHK.basket.Basket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class TotalPriceCalculatorTest {

    private TotalPriceCalculator totalPriceCalculator;
    private SpecialOfferApplier specialOfferApplier;

    @BeforeEach
    void setUp() {
        specialOfferApplier = mock(SpecialOfferApplier.class);
        totalPriceCalculator = new TotalPriceCalculator(specialOfferApplier);
    }

    @Test
    public void shouldCalculateTotalPrice() {
        int totalPrice = totalPriceCalculator.computeTotalPrice(Basket.fromInput("A"));

        assertEquals(50, totalPrice);
    }
}