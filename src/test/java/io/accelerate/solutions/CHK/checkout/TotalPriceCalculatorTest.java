package io.accelerate.solutions.CHK.checkout;

import io.accelerate.solutions.CHK.basket.model.Basket;
import io.accelerate.solutions.CHK.basket.model.ItemCount;
import io.accelerate.solutions.CHK.basket.model.ItemType;
import io.accelerate.solutions.CHK.checkout.model.SpecialOffer;
import io.accelerate.solutions.CHK.checkout.model.SpecialOfferResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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
                .itemsOfferWasAppliedTo(List.of(ItemCount.of(ItemType.A, 3)))
                .build();
        when(specialOfferApplier.apply(eq(ItemType.A), eq(4), any(SpecialOffer.class), anyList())).thenReturn(resultFromApply);

        int totalPrice = totalPriceCalculator.computeTotalPrice(Basket.fromInput("AAAA"));

        assertEquals(150, totalPrice);
    }

    @Test
    public void shouldCalculateTotalPriceWithApplicableMultiProductPromotions() {
        SpecialOfferResult resultFromApply = SpecialOfferResult.builder()
                .totalPriceOfBundle(0)
                .itemsOfferWasAppliedTo(List.of(ItemCount.of(ItemType.B, 2)))
                .build();
        when(specialOfferApplier.apply(eq(ItemType.B), eq(2), any(SpecialOffer.class), anyList())).thenReturn(resultFromApply);

        int totalPrice = totalPriceCalculator.computeTotalPrice(Basket.fromInput("BBEE"));

        assertEquals(80, totalPrice, "B items should be free");
    }

    @Test
    public void shouldCalculateTotalPriceWithApplicableCrossProductPromotions() {
        SpecialOfferResult resultFromApply = SpecialOfferResult.builder()
                .totalPriceOfBundle(0)
                .itemsOfferWasAppliedTo(List.of(ItemCount.of(ItemType.B, 2)))
                .build();
        when(specialOfferApplier.apply(eq(ItemType.B), eq(2), any(SpecialOffer.class), anyList())).thenReturn(resultFromApply);

        int totalPrice = totalPriceCalculator.computeTotalPrice(Basket.fromInput("BBEE"));

        assertEquals(80, totalPrice, "B items should be free");
    }
}