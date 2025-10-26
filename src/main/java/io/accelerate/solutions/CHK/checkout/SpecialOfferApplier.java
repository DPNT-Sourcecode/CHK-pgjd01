package io.accelerate.solutions.CHK.checkout;

import io.accelerate.solutions.CHK.basket.model.ItemCount;
import io.accelerate.solutions.CHK.basket.model.ItemType;
import io.accelerate.solutions.CHK.checkout.model.SpecialOffer;
import io.accelerate.solutions.CHK.checkout.model.SpecialOfferResult;

import java.util.List;

public class SpecialOfferApplier {

    public SpecialOfferResult apply(ItemType itemType, int itemAmount, SpecialOffer offer, List<ItemCount> items) {
        int amountOfItemsWithOfferApplied;
        int totalPriceOfAppliedOffer;
        int numberOfBundles = itemAmount / offer.getSpecialOfferPrice().getCount();

        if (offer.isMultiProductPromotion()) {
            int amountOfOtherProductNeededForOffer = items.stream()
                    .filter(item ->  offer.getTargetTypes().contains(item.getType()))
                    .findFirst().map(ItemCount::getCount).orElse(0);
            int numberOfAllowedBundles = amountOfOtherProductNeededForOffer / offer.getTargetAmount();
            numberOfBundles = Math.min(numberOfAllowedBundles, numberOfBundles);
        }

        totalPriceOfAppliedOffer = numberOfBundles * offer.getSpecialOfferPrice().getPrice();
        amountOfItemsWithOfferApplied = numberOfBundles * offer.getSpecialOfferPrice().getCount();

        return SpecialOfferResult.builder()
                .itemsOfferWasAppliedTo(List.of(ItemCount.of(itemType, amountOfItemsWithOfferApplied)))
                .totalPriceOfBundle(totalPriceOfAppliedOffer)
                .build();
    }
}

