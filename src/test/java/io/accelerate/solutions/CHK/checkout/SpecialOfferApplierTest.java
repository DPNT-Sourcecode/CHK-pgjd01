package io.accelerate.solutions.CHK.checkout;

import io.accelerate.solutions.CHK.basket.ItemCount;
import io.accelerate.solutions.CHK.basket.ItemType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SpecialOfferApplierTest {

    private SpecialOfferApplier specialOfferApplier;

    @BeforeEach
    void setUp() {
        specialOfferApplier = new SpecialOfferApplier();
    }

    @Test
    public void shouldApplySelfProductOffer() {
        SpecialOffer specialOffer = SpecialOffer.of(ItemType.B, 2, 10);

        SpecialOfferResult specialOfferResult = specialOfferApplier.apply(5, specialOffer, List.of());

        assertNotNull(specialOfferResult);
        assertEquals(4, specialOfferResult.getAmountOfItemsAppliedTo());
        assertEquals(20, specialOfferResult.getTotalPriceOfBundle());
    }

    @Test
    public void shouldApplyMultiProductOffer() {
        SpecialOffer specialOffer = SpecialOffer.ofMultiProduct(ItemType.E, 4, SpecialOfferPrice.of(ItemType.B, 2, 10));

        SpecialOfferResult specialOfferResult = specialOfferApplier.apply(5, specialOffer, List.of(ItemCount.of(ItemType.E, 8)));

        assertNotNull(specialOfferResult);
        assertEquals(4, specialOfferResult.getAmountOfItemsAppliedTo());
        assertEquals(20, specialOfferResult.getTotalPriceOfBundle());
    }
}
