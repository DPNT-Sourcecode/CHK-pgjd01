package io.accelerate.solutions.CHK.checkout;

import io.accelerate.solutions.CHK.basket.model.Basket;
import io.accelerate.solutions.CHK.basket.model.ItemCount;
import io.accelerate.solutions.CHK.basket.model.ItemType;
import io.accelerate.solutions.CHK.checkout.model.Any;
import io.accelerate.solutions.CHK.checkout.model.SpecialOffer;
import io.accelerate.solutions.CHK.checkout.model.SpecialOfferPrice;
import io.accelerate.solutions.CHK.checkout.model.SpecialOfferResult;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static io.accelerate.solutions.CHK.basket.model.ItemType.*;

@AllArgsConstructor
public class TotalPriceCalculator {

    private final SpecialOfferApplier specialOfferApplier;
    private final CrossProductOfferApplier crossProductOfferApplier;

    private static final List<SpecialOffer> SPECIAL_OFFERS = new ArrayList<>(List.of(
        SpecialOffer.of(ItemType.A, 3, 130),
        SpecialOffer.of(ItemType.A, 5, 200),
        SpecialOffer.of(ItemType.B, 2, 45),
        SpecialOffer.ofMultiProduct(ItemType.E, 2, SpecialOfferPrice.of(ItemType.B, 1, 0)),
        SpecialOffer.of(ItemType.F, 3, 20),
        SpecialOffer.of(ItemType.H, 5, 45),
        SpecialOffer.of(ItemType.H, 10, 80),
        SpecialOffer.of(ItemType.K, 2, 150),
        SpecialOffer.ofMultiProduct(ItemType.N, 3, SpecialOfferPrice.of(ItemType.M, 1, 0)),
        SpecialOffer.of(ItemType.P, 5, 200),
        SpecialOffer.of(ItemType.Q, 3, 80),
        SpecialOffer.ofMultiProduct(ItemType.R, 3, SpecialOfferPrice.of(ItemType.Q, 1, 0)),
        SpecialOffer.of(ItemType.U, 4, 120),
        SpecialOffer.of(ItemType.V, 2, 90),
        SpecialOffer.of(ItemType.V, 3, 130),
        SpecialOffer.ofCrossProduct(Any.of(S, T, X, Y, Z), 3, 45)
    ));

    private static final List<SpecialOffer> CROSS_PRODUCT_OFFERS = List.of(
            SpecialOffer.ofCrossProduct(Any.of(S, T, X, Y, Z), 3, 45)
    );

    static {
        SPECIAL_OFFERS.sort(Comparator.comparing(SpecialOffer::getDiscountToApply).reversed());
    }

    public int computeTotalPrice(Basket basket) {
        List<ItemCount> items = basket.getItemCounts();

        int totalPrice = items.stream()
                .mapToInt(item -> computeItemPrice(item, items))
                .sum();

        for (SpecialOffer crossProductOffer : CROSS_PRODUCT_OFFERS) {
            SpecialOfferResult specialOfferResult = crossProductOfferApplier.apply(items, crossProductOffer);
            totalPrice -= specialOfferResult.getTotalDiscount();

            //need to remove the items used from the list
        }
        return totalPrice;
    }

    private int computeItemPrice(ItemCount itemCount, List<ItemCount> items) {
        int itemAmount = itemCount.getCount();
        int totalPrice = 0;
        List<ItemCount> itemsToProcess = new ArrayList<>(items);

        ItemType typeBeingProcessed = itemCount.getType();
        for (SpecialOffer offer : SPECIAL_OFFERS) {
            if (offerIsApplicableForThisItem(itemCount, offer)
            && enoughItemsInBasketToApplyOffer(offer, itemAmount)) {
                SpecialOfferResult specialOfferResult = specialOfferApplier.apply(typeBeingProcessed, itemAmount, offer, itemsToProcess);
                totalPrice += specialOfferResult.getTotalPriceOfBundle();
                itemAmount -= specialOfferResult.getAmountAppliedTo(typeBeingProcessed);
            }
        }

        totalPrice += itemAmount * typeBeingProcessed.getBasePrice();

        return totalPrice;
    }

    private static boolean enoughItemsInBasketToApplyOffer(SpecialOffer offer, int itemAmount) {
        return itemAmount >= offer.getSpecialOfferPrice().getCount();

    }

    private static boolean offerIsApplicableForThisItem(ItemCount itemCount, SpecialOffer offer) {
        return offer.getItemTypesApplicableForDiscount().contains(itemCount.getType());
    }
}

