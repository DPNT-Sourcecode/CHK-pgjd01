package io.accelerate.solutions.CHK.basket;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ItemType {

    A(50, SpecialOffer.of(3, 130)),
    B(30, SpecialOffer.of(2, 45)),
    C(20, null),
    D(15, null),
    INVALID(-1, null);

    private final int basePrice;
    private final SpecialOffer specialOffer;
}
