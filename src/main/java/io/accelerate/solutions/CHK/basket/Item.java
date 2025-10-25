package io.accelerate.solutions.CHK.basket;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Getter
@Builder
@ToString
@EqualsAndHashCode
public class Item {

    ItemType type;
    int count;

    public static final Item INVALID = Item.builder()
            .type(ItemType.INVALID)
            .count(1)
            .build();

    public static Item buildItemFromCountedChar(Map.Entry<Character, Long> countedItems) {
        return Item.builder()
                .type(ItemType.valueOf(String.valueOf(countedItems.getKey())))
                .count((int) (long) countedItems.getValue())
                .build();
    }

    public int computeTotalPrice() {
        int numberOfItemsOutsideSpecialOffer = count;
        int priceWithSpecialOfferApplied = 0;
        if (type.getSpecialOffer() != null) {
            int numberOfBundlesWithinSpecialOffer = count / type.getSpecialOffer().getItemCount();
            numberOfItemsOutsideSpecialOffer = count % type.getSpecialOffer().getItemCount();
            priceWithSpecialOfferApplied = numberOfBundlesWithinSpecialOffer * type.getSpecialOffer().getTotalPrice();
        }
        int priceWithoutSpecialOffer = numberOfItemsOutsideSpecialOffer * type.getBasePrice();
        return priceWithoutSpecialOffer + priceWithSpecialOfferApplied;
    }
}

