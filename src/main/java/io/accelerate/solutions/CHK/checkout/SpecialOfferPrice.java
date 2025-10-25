package io.accelerate.solutions.CHK.checkout;

import io.accelerate.solutions.CHK.basket.ItemCount;
import io.accelerate.solutions.CHK.basket.ItemType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SpecialOfferPrice {
    private ItemCount itemCount;
    private int price;

    public static SpecialOfferPrice of(ItemCount itemCount, int price) {
        return new SpecialOfferPrice(itemCount, price);
    }

    public static SpecialOfferPrice of(ItemType discountedType, int itemAmount, int price) {
        return new SpecialOfferPrice(ItemCount.of(discountedType, itemAmount), price);
    }
}

