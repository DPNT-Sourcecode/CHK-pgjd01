package io.accelerate.solutions.CHK.checkout;

import io.accelerate.solutions.CHK.basket.model.ItemCount;
import io.accelerate.solutions.CHK.basket.model.ItemType;
import io.accelerate.solutions.CHK.checkout.model.Any;
import io.accelerate.solutions.CHK.checkout.model.SpecialOffer;
import io.accelerate.solutions.CHK.checkout.model.SpecialOfferPrice;
import io.accelerate.solutions.CHK.checkout.model.SpecialOfferResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.accelerate.solutions.CHK.basket.model.ItemType.*;
import static org.junit.jupiter.api.Assertions.*;

class SpecialOfferApplierTest {

    private SpecialOfferApplier specialOfferApplier;

    @BeforeEach
    void setUp() {
        specialOfferApplier = new SpecialOfferApplier();
    }

    @Test
    public void shouldApplySelfProductOffer() {
        SpecialOffer specialOffer = SpecialOffer.of(B, 2, 10);

        SpecialOfferResult specialOfferResult = specialOfferApplier.apply(B, 5, specialOffer, List.of());

        assertNotNull(specialOfferResult);
        assertEquals(1, specialOfferResult.getItemsOfferWasAppliedTo().size());
        assertEquals(4, specialOfferResult.getAmountAppliedTo(B));
        assertEquals(20, specialOfferResult.getTotalPriceOfBundle());
    }

    @Test
    public void shouldApplyMultiProductOffer() {
        SpecialOffer specialOffer = SpecialOffer.ofMultiProduct(ItemType.E, 4, SpecialOfferPrice.of(B, 2, 10));

        SpecialOfferResult specialOfferResult = specialOfferApplier.apply(ItemType.E, 5, specialOffer, List.of(ItemCount.of(ItemType.E, 8)));

        assertNotNull(specialOfferResult);
        assertEquals(1, specialOfferResult.getItemsOfferWasAppliedTo().size());
        assertEquals(4, specialOfferResult.getAmountAppliedTo(ItemType.E));
        assertEquals(20, specialOfferResult.getTotalPriceOfBundle());
    }

    @Test
    public void shouldApplyCrossProductOffer() {
        SpecialOffer specialOffer = SpecialOffer.ofCrossProduct(Any.of(B, C, D), 4, 10);

        List<ItemCount> itemsToProcess = List.of(ItemCount.of(B, 2), ItemCount.of(C, 2));
        SpecialOfferResult specialOfferResult = specialOfferApplier.apply(ItemType.B, 5, specialOffer, itemsToProcess);

        assertNotNull(specialOfferResult);
        assertEquals(2, specialOfferResult.getItemsOfferWasAppliedTo().size());
        assertTrue(specialOfferResult.getItemsOfferWasAppliedTo().contains(ItemCount.of(B, 2)));
        assertTrue(specialOfferResult.getItemsOfferWasAppliedTo().contains(ItemCount.of(C, 2)));
        assertEquals(10, specialOfferResult.getTotalPriceOfBundle());
    }
}