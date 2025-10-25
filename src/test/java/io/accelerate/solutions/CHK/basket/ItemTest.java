package io.accelerate.solutions.CHK.basket;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemTest {

    @Test
    public void shouldComputeTotal() {
        Item item = Item.builder().count(2).type(ItemType.A).build();

        assertEquals(100, item.computeTotalPrice());
    }

    @Test
    public void shouldComputeTotalPriceWithSpecialOffer() {
        Item item = Item.builder().count(4).type(ItemType.A).build();

        assertEquals(180, item.computeTotalPrice());
    }
}