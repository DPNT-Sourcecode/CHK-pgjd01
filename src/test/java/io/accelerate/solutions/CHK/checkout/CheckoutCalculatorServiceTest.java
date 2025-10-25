package io.accelerate.solutions.CHK.checkout;

import io.accelerate.solutions.CHK.basket.Basket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutCalculatorServiceTest {

    private CheckoutCalculatorService checkoutCalculatorService;

    @BeforeEach
    void setUp() {
        checkoutCalculatorService = new CheckoutCalculatorService();
    }

    @Test
    public void shouldCalculatePriceOfBasket() {
        Basket inputBasket = Basket.fromInput("2A");

        int totalPrice = checkoutCalculatorService.calculate(inputBasket);

        assertEquals(100, totalPrice);
    }

}