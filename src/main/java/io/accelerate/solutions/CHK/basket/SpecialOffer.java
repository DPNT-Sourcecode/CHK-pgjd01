package io.accelerate.solutions.CHK.basket;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SpecialOffer {

    private int itemCount;
    private int totalPrice;

    public static SpecialOffer of(int itemCount, int totalPrice) {
        return new SpecialOffer(itemCount, totalPrice);
    }

}
