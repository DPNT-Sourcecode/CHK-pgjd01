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
        List <Item> items = Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Item::fromInput).collect(Collectors.toList());
        return new Basket(List.of(item, item, item, item));
    }


}

