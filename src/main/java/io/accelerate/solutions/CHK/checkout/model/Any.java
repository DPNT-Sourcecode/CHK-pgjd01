package io.accelerate.solutions.CHK.checkout.model;

import io.accelerate.solutions.CHK.basket.model.ItemType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Any {

    private final Set<ItemType> items;

    public static Any of(ItemType... types) {
        return new Any(Arrays.stream(types).collect(Collectors.toSet()));
    }
}

