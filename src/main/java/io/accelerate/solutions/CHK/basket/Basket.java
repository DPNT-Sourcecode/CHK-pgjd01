package io.accelerate.solutions.CHK.basket;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Basket {
    private List<Item> items;

    public static Basket fromInput(String input) {
        return new Basket(List.of());
    }


}

