package io.accelerate.solutions.CHK.basket;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ItemTest {

    @Test
    public void shouldCreateItem() {
        Item validItem = Item.fromInput("2C");

        assertEquals(ItemType.C, validItem.getType());
        assertEquals(2, validItem.getCount());
    }

    @Test
    public void shouldThrowExceptionWhenInvalid() {
        assertThrows(
                IllegalArgumentException.class,
                () ->Item.fromInput("invalid2")
        );
    }
}