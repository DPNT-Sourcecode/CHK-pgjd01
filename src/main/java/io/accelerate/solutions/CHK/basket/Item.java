package io.accelerate.solutions.CHK.basket;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class Item {
    ItemType type;
    int count;

    public static Item fromInput(String s) {
        
    }
}
