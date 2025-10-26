package io.accelerate.solutions.CHK.checkout.model;

import io.accelerate.solutions.CHK.basket.model.ItemCount;
import io.accelerate.solutions.CHK.basket.model.ItemType;
import lombok.Getter;

@Getter
public class SpecialOffer {

    private final ItemCount target;
    private final SpecialOfferPrice specialOfferPrice;
    private final int discountToApply;
    private final ItemType itemTypeApplicableForDiscount;
    private final boolean isMultiProductPromotion;

    public static SpecialOffer of(ItemType itemType, int count, int totalPrice) {
        ItemCount itemCount = ItemCount.of(itemType, count);
        return new SpecialOffer(
                itemCount,
                SpecialOfferPrice.of(itemCount, totalPrice)
        );
    }

    public static SpecialOffer ofMultiProduct(ItemType itemType, int count, SpecialOfferPrice specialOfferPrice) {
        return new SpecialOffer(ItemCount.of(itemType, count), specialOfferPrice);
    }

    public static SpecialOffer ofCrossProduct(Any anyItems, int count, int totalPrice) {
        return null;
    }

    private SpecialOffer(ItemCount target, SpecialOfferPrice specialOfferPrice) {
        this.target = target;
        this.specialOfferPrice = specialOfferPrice;
        this.discountToApply = specialOfferPrice.getItemCount().computeBasePrice() - specialOfferPrice.getPrice();
        this.itemTypeApplicableForDiscount = specialOfferPrice.getItemCount().getType();
        this.isMultiProductPromotion = itemTypeApplicableForDiscount != target.getType();
    }

}
