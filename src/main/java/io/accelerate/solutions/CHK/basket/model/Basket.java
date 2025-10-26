package io.accelerate.solutions.CHK.basket.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Basket {
    private List<ItemCount> itemCounts;

    public static Basket fromInput(String input) {
        List<ItemCount> itemCounts;
        try {
            itemCounts = input.chars()
                    .mapToObj(value -> (char) value)
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                    .entrySet().stream()
                    .map(ItemCount::buildItemFromCountedChar)
                    .toList();
        } catch (IllegalArgumentException e) {
            itemCounts = List.of(ItemCount.INVALID);
        }
        return new Basket(itemCounts);
    }
}
