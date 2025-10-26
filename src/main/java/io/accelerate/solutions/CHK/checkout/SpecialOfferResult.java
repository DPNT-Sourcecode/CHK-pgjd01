package io.accelerate.solutions.CHK.checkout;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SpecialOfferResult {
    int amountOfItemsAppliedTo;
    int totalPriceOfBundle;
}
