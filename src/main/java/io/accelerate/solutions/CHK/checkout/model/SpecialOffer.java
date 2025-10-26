package io.accelerate.solutions.CHK.checkout.model;

import io.accelerate.solutions.CHK.basket.model.ItemCount;
import io.accelerate.solutions.CHK.basket.model.ItemType;
import lombok.Getter;

import java.util.Set;

@Getter
public class SpecialOffer {

    private final Set<ItemType> targetTypes;
    private final int targetAmount;
    private final SpecialOfferPrice specialOfferPrice;
    private final int discountToApply;
    private final ItemType itemTypeApplicableForDiscount;
    private final boolean isMultiProductPromotion;

    public static SpecialOffer of(ItemType itemType, int count, int totalPrice) {
        ItemCount itemCount = ItemCount.of(itemType, count);
        return new SpecialOffer(
                Set.of(itemType),
                itemCount.getCount(),
                SpecialOfferPrice.of(itemCount, totalPrice)
        );
    }

    public static SpecialOffer ofMultiProduct(ItemType itemType, int count, SpecialOfferPrice specialOfferPrice) {
        return new SpecialOffer(Set.of(itemType), count, specialOfferPrice);
    }

    public static SpecialOffer ofCrossProduct(Any anyItems, int count, int totalPrice) {
        return null;
    }

    private SpecialOffer(Set<ItemType> targetTypes, int targetAmount, SpecialOfferPrice specialOfferPrice) {
        this.targetTypes = targetTypes;
        this.targetAmount = targetAmount;
        this.specialOfferPrice = specialOfferPrice;
        this.discountToApply = specialOfferPrice.getItemCount().computeBasePrice() - specialOfferPrice.getPrice();
        this.itemTypeApplicableForDiscount = specialOfferPrice.getItemCount().getType();
        this.isMultiProductPromotion = targetTypes.size() != 1 || !targetTypes.contains(specialOfferPrice.getItemCount().getType());
    }

}

