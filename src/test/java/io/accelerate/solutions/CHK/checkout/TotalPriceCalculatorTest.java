package io.accelerate.solutions.CHK.checkout;

import io.accelerate.solutions.CHK.basket.Basket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

    @Test
    public void shouldCalculateTotalPriceWithApplicableSelfProductPromotions() {
        SpecialOfferResult resultFromApply = SpecialOfferResult.builder()
                .totalPriceOfBundle(100)
                .amountOfItemsAppliedTo(3)
                .build();
        when(specialOfferApplier.apply(eq(4), any(SpecialOffer.class), anyList())).thenReturn(resultFromApply);

        int totalPrice = totalPriceCalculator.computeTotalPrice(Basket.fromInput("AAAA"));

        assertEquals(150, totalPrice);
    }

    @Test
    public void shouldCalculateTotalPriceWithApplicableMultiProductPromotions() {
        SpecialOfferResult resultFromApply = SpecialOfferResult.builder()
                .totalPriceOfBundle(0)
                .amountOfItemsAppliedTo(2)
                .build();
        when(specialOfferApplier.apply(eq(2), any(SpecialOffer.class), anyList())).thenReturn(resultFromApply);

        int totalPrice = totalPriceCalculator.computeTotalPrice(Basket.fromInput("BBEE"));

        assertEquals(40, totalPrice, "B items should be free");
    }
}