package io.accelerate.solutions.CHK.basket;

import io.accelerate.solutions.CHK.basket.model.Basket;
import io.accelerate.solutions.CHK.basket.model.ItemCount;
import io.accelerate.solutions.CHK.basket.model.ItemType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BasketTest {

    private Basket basket;

    @Test
    public void shouldComputeBasketFromInput() {
        basket = Basket.fromInput("AAAADBBBCC");

        List<ItemCount> itemsInTheBasket = basket.getItemCounts();
        assertNotNull(itemsInTheBasket);
        assertEquals(itemsInTheBasket.size(), 4);
        assertTrue(itemsInTheBasket.contains(ItemCount.builder().type(ItemType.A).count(4).build()));
        assertTrue(itemsInTheBasket.contains(ItemCount.builder().type(ItemType.B).count(3).build()));
        assertTrue(itemsInTheBasket.contains(ItemCount.builder().type(ItemType.C).count(2).build()));
        assertTrue(itemsInTheBasket.contains(ItemCount.builder().type(ItemType.D).count(1).build()));
    }
}