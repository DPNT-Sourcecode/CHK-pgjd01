package io.accelerate.solutions.CHK.checkout;

import io.accelerate.solutions.CHK.basket.ItemCount;
import io.accelerate.solutions.CHK.basket.ItemType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SpecialOffer {

    private ItemCount target;
    private SpecialOfferPrice specialOfferPrice;

    public static SpecialOffer of(ItemType itemType, Map<Integer, Integer> offerMap) {
        ItemCount itemCount = ItemCount.of(itemType, count);
        return new SpecialOffer(
                itemCount,
                SpecialOfferPrice.of(itemCount, totalPrice)
        );
    }

    public static SpecialOffer of(ItemType itemType, int count, SpecialOfferPrice specialOfferPrice) {
        return new SpecialOffer(ItemCount.of(itemType, count), specialOfferPrice);
    }

}
