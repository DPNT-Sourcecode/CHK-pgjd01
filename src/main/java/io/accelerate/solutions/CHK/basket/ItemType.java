package io.accelerate.solutions.CHK.basket;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ItemType {

    A(50),
    B(30),
    C(20),
    D(15),
    E(40),
    INVALID(-1);

    private final int basePrice;
}
