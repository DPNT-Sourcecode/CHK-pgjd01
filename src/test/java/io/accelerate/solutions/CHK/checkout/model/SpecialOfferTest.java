package io.accelerate.solutions.CHK.checkout.model;

import io.accelerate.solutions.CHK.basket.model.ItemType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpecialOfferTest {

    @Test
    void testOf() {
        SpecialOffer specialOffer = SpecialOffer.of(ItemType.B, 3, 40);

        assertEquals(specialOffer.getTarget().getType(), ItemType.B);
        assertEquals(specialOffer.getTarget().getCount(), 3);
        assertEquals(specialOffer.getItemTypeApplicableForDiscount(), ItemType.B);
        assertFalse(specialOffer.isMultiProductPromotion());
    }

    @Test
    void ofMultiProduct() {
    }

    @Test
    void ofCrossProduct() {
    }
}
