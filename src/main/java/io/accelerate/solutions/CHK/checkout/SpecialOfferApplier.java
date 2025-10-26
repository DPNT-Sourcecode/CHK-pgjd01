package io.accelerate.solutions.CHK.checkout;

import io.accelerate.solutions.CHK.basket.ItemCount;

import java.util.List;

public class SpecialOfferApplier {

    public SpecialOfferResult apply(int itemAmount, SpecialOffer offer, List<ItemCount> items) {
        // check for applicability of "other-products offers" comparing offer target and items list

        // if no "other-products applies" apply self-product offer

        // compute result


        return SpecialOfferResult.builder().build();
    }
}


