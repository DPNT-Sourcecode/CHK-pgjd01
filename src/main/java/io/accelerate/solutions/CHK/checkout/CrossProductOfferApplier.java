package io.accelerate.solutions.CHK.checkout;

import io.accelerate.solutions.CHK.basket.model.ItemCount;
import io.accelerate.solutions.CHK.checkout.model.SpecialOffer;
import io.accelerate.solutions.CHK.checkout.model.SpecialOfferResult;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CrossProductOfferApplier {

    public SpecialOfferResult apply(List<ItemCount> itemsInBasket, SpecialOffer crossProductOffer) {
        List<ItemCount> applicableItems = new ArrayList<>(itemsInBasket.stream()
                .filter(item -> crossProductOffer.getTargetTypes().contains(item.getType())).toList());

        applicableItems.sort(Comparator.comparing(ItemCount::computeBasePrice).reversed());

        return null;
    }
}
