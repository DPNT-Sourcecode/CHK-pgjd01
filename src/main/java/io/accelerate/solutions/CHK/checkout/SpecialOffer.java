package io.accelerate.solutions.CHK.checkout;

import io.accelerate.solutions.CHK.basket.ItemCount;
import io.accelerate.solutions.CHK.basket.ItemType;
import lombok.Getter;

@Getter
public class SpecialOffer {

    private final ItemCount target;
    private final SpecialOfferPrice specialOfferPrice;
    private final int discountToApply;
    private final ItemType itemTypeApplicableForDiscount;

    public static SpecialOffer of(ItemType itemType, int count, int totalPrice) {
        ItemCount itemCount = ItemCount.of(itemType, count);
        return new SpecialOffer(
                itemCount,
                SpecialOfferPrice.of(itemCount, totalPrice)
        );
    }

    public static SpecialOffer of(ItemType itemType, int count, SpecialOfferPrice specialOfferPrice) {
        return new SpecialOffer(ItemCount.of(itemType, count), specialOfferPrice);
    }

    private SpecialOffer(ItemCount target, SpecialOfferPrice specialOfferPrice) {
        this.target = target;
        this.specialOfferPrice = specialOfferPrice;
        this.discountToApply = specialOfferPrice.getItemCount().computeBasePrice() - specialOfferPrice.getPrice();
        this.itemTypeApplicableForDiscount = specialOfferPrice.getItemCount().getType();
    }

}
