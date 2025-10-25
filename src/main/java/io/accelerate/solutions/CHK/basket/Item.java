package io.accelerate.solutions.CHK.basket;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Builder
@EqualsAndHashCode
public class Item {
    ItemType type;
    int count;

    private static String REGEX = "(\\d+)([a-zA-Z]+)";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    public static Item fromInput(String input) {
        Matcher matcher = PATTERN.matcher(input);
        return Item.builder()
                .count(Integer.parseInt(matcher.group(1)))
                .type(ItemType.valueOf(matcher.group(2)))
                .build();
    }
}

