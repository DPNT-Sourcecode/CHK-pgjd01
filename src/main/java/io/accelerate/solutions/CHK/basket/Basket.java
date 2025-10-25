package io.accelerate.solutions.CHK.basket;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Basket {
    private List<Item> items;

    public static Basket fromInput(String input) {
        List <Item> items;
        try {
            items = Arrays.stream(input.split(","))
                    .map(String::trim)
                    .map(Item::fromInput)
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            items = List.of(Item.INVALID);
        }
        return new Basket(items);
    }

    public int computeTotalPrice() {
        return items.stream()
                .mapToInt(item -> item.computeTotalPrice())
                .sum();
    }
}

