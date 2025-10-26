package io.accelerate.solutions.CHK.basket.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Getter
@Builder
@ToString
@EqualsAndHashCode
public class ItemCount {

    ItemType type;
    int count;

    public static final ItemCount INVALID = ItemCount.builder()
            .type(ItemType.INVALID)
            .count(1)
            .build();

    public static ItemCount buildItemFromCountedChar(Map.Entry<Character, Long> countedItems) {
        return ItemCount.builder()
                .type(ItemType.valueOf(String.valueOf(countedItems.getKey())))
                .count((int) (long) countedItems.getValue())
                .build();
    }

    public static ItemCount of(ItemType itemType, int count) {
        return new ItemCount(itemType, count);
    }

    public int computeBasePrice() {
        return type.getBasePrice() * count;
    }
}
