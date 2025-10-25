package io.accelerate.solutions.CHK.basket;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BasketTest {

    private Basket basket;

    @Test
    public void shouldComputeBasketFromInput() {
        basket = Basket.fromInput("4A, 3B, 2C, 1D");

        List<Item> itemsInTheBasket = basket.getItems();
        assertNotNull(itemsInTheBasket);
        assertEquals(itemsInTheBasket.size(), 4);
        assertTrue(itemsInTheBasket.contains(Item.builder().type(ItemType.A).count(4).build()));
        assertTrue(itemsInTheBasket.contains(Item.builder().type(ItemType.B).count(3).build()));
        assertTrue(itemsInTheBasket.contains(Item.builder().type(ItemType.C).count(2).build()));
        assertTrue(itemsInTheBasket.contains(Item.builder().type(ItemType.D).count(1).build()));
    }
}
