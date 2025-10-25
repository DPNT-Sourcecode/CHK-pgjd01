package io.accelerate.solutions.CHK.basket;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Basket {
    private List<Item> items;

    public static Basket fromInput(String input) {
        List<Item> items;
        try {
            items = input.chars()
                    .mapToObj(value -> (char) value)
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                    .entrySet().stream()
                    .map(Item::buildItemFromCountedChar)
                    .toList();
        } catch (IllegalArgumentException e) {
            items = List.of(Item.INVALID);
        }
        return new Basket(items);
    }

    public int computeTotalPrice() {
        return items.stream()
                .mapToInt(Item::computeTotalPrice)
                .sum();
    }
}
