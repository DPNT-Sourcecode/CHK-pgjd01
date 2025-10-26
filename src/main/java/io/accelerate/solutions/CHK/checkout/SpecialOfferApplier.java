package io.accelerate.solutions.CHK.checkout;

import io.accelerate.solutions.CHK.basket.ItemCount;

import java.util.List;

public class SpecialOfferApplier {

    public SpecialOfferResult apply(int itemAmount, SpecialOffer offer, List<ItemCount> items) {
        // check for applicability of "other-products offers" comparing offer target and items list
        int amountOfItemsWithOfferApplied = 0;
        int totalPriceOfAppliedOffer = 0;
        if (offer.isMultiProductPromotion()) {

        } else {
            // if no "other-products applies" apply self-product offer
            int numberOfBundles = itemAmount / offer.getSpecialOfferPrice().getItemCount().getCount();
            totalPriceOfAppliedOffer = numberOfBundles * offer.getSpecialOfferPrice().getPrice();
            amountOfItemsWithOfferApplied = numberOfBundles * offer.getSpecialOfferPrice().getItemCount().getCount();
        }
        // compute result

        return SpecialOfferResult.builder()
                .amountOfItemsAppliedTo(amountOfItemsWithOfferApplied)
                .totalPriceOfBundle(totalPriceOfAppliedOffer)
                .build();
    }
}
