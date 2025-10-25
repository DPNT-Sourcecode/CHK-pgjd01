package io.accelerate.solutions.CHK.basket;

import org.junit.jupiter.api.Test;

import java.util.AbstractMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemTest {

    @Test
    public void shouldBuildFromMapEntry() {
        Map.Entry<Character, Long> charactersCounted = new AbstractMap.SimpleEntry<Character, Long>('B', 4L);

        Item item = Item.buildItemFromCountedChar(charactersCounted);

        assertEquals(item.getType(), ItemType.B);
        assertEquals(item.getCount(), 4);
    }
}