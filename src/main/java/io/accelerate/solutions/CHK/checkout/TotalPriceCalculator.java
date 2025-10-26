package io.accelerate.solutions.CHK.checkout;

import io.accelerate.solutions.CHK.basket.Basket;
import io.accelerate.solutions.CHK.basket.ItemCount;
import io.accelerate.solutions.CHK.basket.ItemType;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
public class TotalPriceCalculator {

    private final SpecialOfferApplier specialOfferApplier;

    private static final List<SpecialOffer> SPECIAL_OFFERS = new ArrayList<>(List.of(
        SpecialOffer.of(ItemType.A, 3, 130),
        SpecialOffer.of(ItemType.A, 5, 200),
        SpecialOffer.of(ItemType.B, 2, 45),
        SpecialOffer.of(ItemType.E, 2, SpecialOfferPrice.of(ItemType.B, 1, 0))
    ));

    static {
        SPECIAL_OFFERS.sort(Comparator.comparing(SpecialOffer::getDiscountToApply).reversed());
    }

    public int computeTotalPrice(Basket basket) {
        List<ItemCount> items = basket.getItemCounts();

        return items.stream()
                .mapToInt(item -> computeItemPrice(item, items))
                .sum();
    }

    private int computeItemPrice(ItemCount itemCount, List<ItemCount> items) {
        int itemAmount = itemCount.getCount();
        int totalPrice = 0;
        List<ItemCount> itemsToProcess = new ArrayList<>(items);

        for (SpecialOffer offer : SPECIAL_OFFERS) {
            if (offerIsApplicableForThisItem(itemCount, offer)
            && enoughItemsInBasketToApplyOffer(offer, itemAmount)) {
                SpecialOfferResult specialOfferResult = specialOfferApplier.apply(itemAmount, offer, itemsToProcess);
                //totalPrice += amountOfItemsOfferAppliedTo * offer.getPricePerDiscountedItem();

                //itemAmount -= amountOfItemsOfferAppliedTo;
            }
        }

        totalPrice += itemAmount * itemCount.getType().getBasePrice();

        return totalPrice;
    }

    private static boolean enoughItemsInBasketToApplyOffer(SpecialOffer offer, int itemAmount) {
        return itemAmount >= offer.getSpecialOfferPrice().getItemCount().getCount();
    }

    private static boolean offerIsApplicableForThisItem(ItemCount itemCount, SpecialOffer offer) {
        return itemCount.getType() == offer.getItemTypeApplicableForDiscount();
    }
}


