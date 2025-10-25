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
}
