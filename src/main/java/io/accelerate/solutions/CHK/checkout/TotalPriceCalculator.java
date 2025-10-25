package io.accelerate.solutions.CHK.checkout;

import io.accelerate.solutions.CHK.basket.Basket;
import io.accelerate.solutions.CHK.basket.ItemCount;
import io.accelerate.solutions.CHK.basket.ItemType;

import java.util.List;
import java.util.Set;

public class TotalPriceCalculator {

    private static final Set<SpecialOffer> SPECIAL_OFFERS = Set.of(
        SpecialOffer.of(ItemType.A, 3, 130),
        SpecialOffer.of(ItemType.A, 5, 200),
        SpecialOffer.of(ItemType.B, 2, 45)
    );

    private static final Set<SpecialOffer> SPECIAL_OFFERS_MULTIPRODUCTS = Set.of(
        SpecialOffer.of(ItemType.E, 2, SpecialOfferPrice.of(ItemType.B, 1, 0))
    );

    public int computeTotalPrice(Basket basket) {
        List<ItemCount> items = basket.getItemCounts();

        int totalBasePriceWithoutDiscounts = items.stream().mapToInt(ItemCount::computeBasePrice).sum();



        return 0;
    }

    private int computeItemPrice(ItemCount itemCount) {
//        int count = itemCount.getCount();
//        int numberOfItemsOutsideSpecialOffer = count;
//        int priceWithSpecialOfferApplied = 0;
//        if (type.getSpecialOffer() != null) {
//            int numberOfBundlesWithinSpecialOffer = count / type.getSpecialOffer().getItemCount();
//            numberOfItemsOutsideSpecialOffer = count % type.getSpecialOffer().getItemCount();
//            priceWithSpecialOfferApplied = numberOfBundlesWithinSpecialOffer * type.getSpecialOffer().getTotalPrice();
//        }
//        int priceWithoutSpecialOffer = numberOfItemsOutsideSpecialOffer * type.getBasePrice();
//        return priceWithoutSpecialOffer + priceWithSpecialOfferApplied;
        return 0;
    }
}

