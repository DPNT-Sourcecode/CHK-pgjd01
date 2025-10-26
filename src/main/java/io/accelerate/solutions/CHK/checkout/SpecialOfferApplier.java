package io.accelerate.solutions.CHK.checkout;

import io.accelerate.solutions.CHK.basket.model.ItemCount;
import io.accelerate.solutions.CHK.checkout.model.SpecialOffer;
import io.accelerate.solutions.CHK.checkout.model.SpecialOfferResult;

import java.util.List;

public class SpecialOfferApplier {

    public SpecialOfferResult apply(int itemAmount, SpecialOffer offer, List<ItemCount> items) {
        int amountOfItemsWithOfferApplied = 0;
        int totalPriceOfAppliedOffer = 0;
        int numberOfBundles = itemAmount / offer.getSpecialOfferPrice().getItemCount().getCount();

        if (offer.isMultiProductPromotion()) {
            int amountOfOtherProductNeededForOffer = items.stream()
                    .filter(item -> item.getType() == offer.getTarget().getType())
                    .findFirst().map(ItemCount::getCount).orElse(0);
            int numberOfAllowedBundles = amountOfOtherProductNeededForOffer / offer.getTarget().getCount();
            numberOfBundles = Math.min(numberOfAllowedBundles, numberOfBundles);
        }

        totalPriceOfAppliedOffer = numberOfBundles * offer.getSpecialOfferPrice().getPrice();
        amountOfItemsWithOfferApplied = numberOfBundles * offer.getSpecialOfferPrice().getItemCount().getCount();

        return SpecialOfferResult.builder()
                .amountOfItemsAppliedTo(amountOfItemsWithOfferApplied)
                .totalPriceOfBundle(totalPriceOfAppliedOffer)
                .build();
    }
}



