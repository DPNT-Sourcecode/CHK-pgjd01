package io.accelerate.solutions.CHK.checkout.model;

import io.accelerate.solutions.CHK.basket.model.ItemCount;
import io.accelerate.solutions.CHK.basket.model.ItemType;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class SpecialOfferResult {
    List<ItemCount> itemsOfferWasAppliedTo;
    int totalPriceOfBundle;

    public int getAmountAppliedTo(ItemType type) {
        return itemsOfferWasAppliedTo.stream().filter(item -> item.getType() == type)
                .findFirst()
                .stream().mapToInt(ItemCount::getCount).findAny().orElse(0);
    }
}
