package io.accelerate.solutions.CHK.checkout;

import io.accelerate.solutions.CHK.basket.Item;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SpecialOffer {

    private Item target;
    private List<Item> specialPrices;

    public static SpecialOffer of(int itemCount, int totalPrice) {
        return new SpecialOffer(itemCount, totalPrice);
    }

}