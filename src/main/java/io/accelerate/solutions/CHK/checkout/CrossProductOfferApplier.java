package io.accelerate.solutions.CHK.checkout;

import io.accelerate.solutions.CHK.basket.model.ItemCount;
import io.accelerate.solutions.CHK.basket.model.ItemType;
import io.accelerate.solutions.CHK.checkout.model.CrossProductBundle;
import io.accelerate.solutions.CHK.checkout.model.SpecialOffer;
import io.accelerate.solutions.CHK.checkout.model.SpecialOfferResult;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CrossProductOfferApplier {

    public SpecialOfferResult apply(List<ItemCount> itemsInBasket, SpecialOffer crossProductOffer) {
        List<ItemCount> applicableItems = new ArrayList<>(itemsInBasket.stream()
                .filter(item -> crossProductOffer.getTargetTypes().contains(item.getType()))
                .sorted(Comparator.comparing(ItemCount::computeBasePrice).reversed())
                .toList());

        int totalPrice = 0;

        CrossProductBundle currentBundle = new CrossProductBundle();
        List<ItemCount> itemsToWhichOfferApplied = new ArrayList<>();
        for (ItemCount applicableItem : applicableItems) {
            if (enoughItemsToApplyOffer(crossProductOffer, applicableItem, currentBundle)) {
                // close bundle, apply price and register applicable products
                totalPrice += crossProductOffer.getSpecialOfferPrice().getPrice();
                int amountOfItemsNeededToCloseBundle = crossProductOffer.getTargetAmount() - currentBundle.getCurrentNumberOfItems();
                currentBundle.addItem(applicableItem.getType(), amountOfItemsNeededToCloseBundle);
                itemsToWhichOfferApplied.addAll(currentBundle.getItems());
                currentBundle = new CrossProductBundle();

                // put remaining items in new bundle
                if (applicableItem.getCount() > amountOfItemsNeededToCloseBundle) {
                    currentBundle.addItem(applicableItem.subtractUnits(amountOfItemsNeededToCloseBundle));
                }
            } else {
                currentBundle.addItem(applicableItem);
            }
        }

        return SpecialOfferResult.builder()
                .itemsOfferWasAppliedTo(itemsToWhichOfferApplied) // not needed for now
                .totalPriceOfBundle(totalPrice)
                .build();
    }

    private static boolean enoughItemsToApplyOffer(SpecialOffer crossProductOffer, ItemCount applicableItem, CrossProductBundle currentBundle) {
        return currentBundle.getCurrentNumberOfItems() + applicableItem.getCount() >= crossProductOffer.getTargetAmount();
    }

}
