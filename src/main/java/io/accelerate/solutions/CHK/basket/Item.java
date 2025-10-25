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
    public static final Item INVALID = Item.builder()
            .type(ItemType.INVALID)
            .count(1)
            .build();

    private static String REGEX = "(\\d+)([a-zA-Z]+)";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    public static Item fromInput(String input) {
        Matcher matcher = PATTERN.matcher(input);
        if (matcher.find() && matcher.groupCount() == 2) {
            return Item.builder()
                    .count(Integer.parseInt(matcher.group(1)))
                    .type(ItemType.valueOf(matcher.group(2)))
                    .build();
        } else {
            throw new IllegalArgumentException("Invalid item");
        }
    }

    public int computeTotalPrice() {
        int numberOfItemsOutsideSpecialOffer = count;
        int priceWithSpecialOfferApplied = 0;
        if (type.getSpecialOffer() != null) {
            int numberOfBundlesWithinSpecialOffer = count / type.getSpecialOffer().getItemCount();
            numberOfItemsOutsideSpecialOffer = count % type.getSpecialOffer().getItemCount();
            priceWithSpecialOfferApplied = numberOfBundlesWithinSpecialOffer * type.getSpecialOffer().getTotalPrice();
        }
        int priceWithoutSpecialOffer = numberOfItemsOutsideSpecialOffer * type.getBasePrice();
        return priceWithoutSpecialOffer + priceWithSpecialOfferApplied;
    }
}


