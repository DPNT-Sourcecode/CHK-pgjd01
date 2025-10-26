package io.accelerate.solutions.CHK.checkout.model;

import io.accelerate.solutions.CHK.basket.model.ItemCount;
import io.accelerate.solutions.CHK.basket.model.ItemType;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CrossProductBundle {
    private final List<ItemCount> items = new ArrayList<>();
    private int currentNumberOfItems = 0;

    public void addItem(ItemCount itemCount) {
        items.add(itemCount);
        currentNumberOfItems += itemCount.getCount();
    }

    public void addItem(ItemType type, int amount) {
        items.add(ItemCount.of(type, amount));
        currentNumberOfItems += amount;
    }

    public int getTotalOriginalPrice() {
        return items.stream().mapToInt(ItemCount::computeBasePrice).sum();
    }
}
