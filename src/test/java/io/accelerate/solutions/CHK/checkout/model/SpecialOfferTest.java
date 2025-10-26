package io.accelerate.solutions.CHK.checkout.model;

import io.accelerate.solutions.CHK.basket.model.ItemCount;
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
        SpecialOffer specialOffer = SpecialOffer.ofMultiProduct(ItemType.C, 4, SpecialOfferPrice.of(ItemCount.of(ItemType.A, 3), 20));

        assertEquals(specialOffer.getTarget().getType(), ItemType.C);
        assertEquals(specialOffer.getTarget().getCount(), 4);
        assertEquals(specialOffer.getItemTypeApplicableForDiscount(), ItemType.A);
        assertTrue(specialOffer.isMultiProductPromotion());
    }

    @Test
    void ofCrossProduct() {
        SpecialOffer specialOffer = SpecialOffer.ofCrossProduct(Any.of(ItemType.B, ItemType.C, ItemType.D), 3, 35);

        assertNotNull(specialOffer);
    }
}
