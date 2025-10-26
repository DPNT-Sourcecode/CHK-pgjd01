package io.accelerate.solutions.CHK.checkout.model;

import io.accelerate.solutions.CHK.basket.model.ItemCount;
import io.accelerate.solutions.CHK.basket.model.ItemType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Set;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SpecialOfferPrice {
    private Set<ItemType> discountedTypes;
    private int count;
    private int price;

    public static SpecialOfferPrice of(ItemCount itemCount, int price) {
        return new SpecialOfferPrice(Set.of(itemCount.getType()), itemCount.getCount(), price);
    }

    public static SpecialOfferPrice of(ItemType discountedType, int itemAmount, int price) {
        return new SpecialOfferPrice(Set.of(discountedType), itemAmount, price);
    }

    public static SpecialOfferPrice of(Any anyItems, int itemAmount, int price) {
        return new SpecialOfferPrice(anyItems.getItems(), itemAmount, price);
    }

    public int computeDiscountToApply() {
        if (discountedTypes.size() == 1) {
            ItemType discountedType = new ArrayList<>(discountedTypes).getFirst();
            return ItemCount.of(discountedType, count).computeBasePrice() - price;
        } else {
            // calculate average of all products? Not ideal
            return 0;
        }
    }
}

