package io.accelerate.solutions.CHK.checkout.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SpecialOfferResult {
    int amountOfItemsAppliedTo;
    int totalPriceOfBundle;
}
