package io.accelerate.solutions.CHK.basket;

import org.junit.jupiter.api.Test;

import java.util.AbstractMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemCountTest {

    @Test
    public void shouldBuildFromMapEntry() {
        Map.Entry<Character, Long> charactersCounted = new AbstractMap.SimpleEntry<Character, Long>('B', 4L);

        ItemCount itemCount = ItemCount.buildItemFromCountedChar(charactersCounted);

        assertEquals(itemCount.getType(), ItemType.B);
        assertEquals(itemCount.getCount(), 4);
    }
}