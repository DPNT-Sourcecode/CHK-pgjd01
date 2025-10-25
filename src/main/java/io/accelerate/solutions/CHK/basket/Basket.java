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
//        try {
//            Map<Character, Long> itemsGrouped = input.chars()
//                    .mapToObj(value -> (char) value)
//                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
//                    .forEach((character, count) -> {
//                        items.add(Item.builder().count(count).build());
//                    });
//        } catch (IllegalArgumentException e) {
//            items = List.of(Item.INVALID);
//        }
//        return new Basket(items);
        return null;
    }

    public int computeTotalPrice() {
        return items.stream()
                .mapToInt(item -> item.computeTotalPrice())
                .sum();
    }
}
