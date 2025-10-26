package io.accelerate.solutions.CHK;

import io.accelerate.solutions.CHK.basket.model.Basket;
import io.accelerate.solutions.CHK.checkout.CrossProductOfferApplier;
import io.accelerate.solutions.CHK.checkout.SpecialOfferApplier;
import io.accelerate.solutions.CHK.checkout.TotalPriceCalculator;
import lombok.AccessLevel;
import lombok.Getter;

import javax.swing.plaf.ColorUIResource;

public class CheckoutSolution {

    @Getter(AccessLevel.PACKAGE)
    private final TotalPriceCalculator calculator;

    public CheckoutSolution() {
        this.calculator = new TotalPriceCalculator(new SpecialOfferApplier(), new CrossProductOfferApplier());
    }

    public Integer checkout(String skus) {
        Basket basket = Basket.fromInput(skus);

        return calculator.computeTotalPrice(basket);
    }
}

