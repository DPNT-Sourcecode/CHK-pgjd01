package io.accelerate.solutions.CHK.checkout;

import io.accelerate.solutions.CHK.basket.model.ItemCount;
import io.accelerate.solutions.CHK.basket.model.ItemType;
import io.accelerate.solutions.CHK.checkout.model.SpecialOffer;
import io.accelerate.solutions.CHK.checkout.model.SpecialOfferPrice;
import io.accelerate.solutions.CHK.checkout.model.SpecialOfferResult;
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

        SpecialOfferResult specialOfferResult = specialOfferApplier.apply(ItemType.B, 5, specialOffer, List.of());

        assertNotNull(specialOfferResult);
        assertEquals(1, specialOfferResult.getItemsOfferWasAppliedTo().size());
        assertEquals(4, specialOfferResult.getAmountAppliedTo(ItemType.B));
        assertEquals(20, specialOfferResult.getTotalPriceOfBundle());
    }

    @Test
    public void shouldApplyMultiProductOffer() {
        SpecialOffer specialOffer = SpecialOffer.ofMultiProduct(ItemType.E, 4, SpecialOfferPrice.of(ItemType.B, 2, 10));

        SpecialOfferResult specialOfferResult = specialOfferApplier.apply(ItemType.E, 5, specialOffer, List.of(ItemCount.of(ItemType.E, 8)));

        assertNotNull(specialOfferResult);
        assertEquals(1, specialOfferResult.getItemsOfferWasAppliedTo().size());
        assertEquals(4, specialOfferResult.getAmountAppliedTo(ItemType.E));
        assertEquals(20, specialOfferResult.getTotalPriceOfBundle());
    }
}
