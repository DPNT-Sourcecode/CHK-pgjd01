package io.accelerate.solutions.CHK.checkout;

import io.accelerate.solutions.CHK.basket.model.ItemCount;
import io.accelerate.solutions.CHK.basket.model.ItemType;
import io.accelerate.solutions.CHK.checkout.model.Any;
import io.accelerate.solutions.CHK.checkout.model.SpecialOffer;
import io.accelerate.solutions.CHK.checkout.model.SpecialOfferResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.accelerate.solutions.CHK.basket.model.ItemType.*;
import static org.junit.jupiter.api.Assertions.*;

class CrossProductOfferApplierTest {

    private CrossProductOfferApplier crossProductOfferApplier;

    @BeforeEach
    void setUp() {
        crossProductOfferApplier = new CrossProductOfferApplier();
    }

    @Test
    public void shouldApplyCrossProductOffer() {
        SpecialOffer crossProductOffer = SpecialOffer.ofCrossProduct(Any.of(B, C, D), 4, 10);

        List<ItemCount> itemsToProcess = List.of(ItemCount.of(B, 2), ItemCount.of(C, 2));
        SpecialOfferResult specialOfferResult = crossProductOfferApplier.apply(itemsToProcess, crossProductOffer);

        assertNotNull(specialOfferResult);
        //assertEquals(2, specialOfferResult.getItemsOfferWasAppliedTo().size());
        //assertTrue(specialOfferResult.getItemsOfferWasAppliedTo().contains(ItemCount.of(B, 2)));
        //assertTrue(specialOfferResult.getItemsOfferWasAppliedTo().contains(ItemCount.of(C, 2)));
        assertEquals(10, specialOfferResult.getTotalPriceOfBundle());
        assertEquals(90, specialOfferResult.getTotalDiscount());
    }

}
