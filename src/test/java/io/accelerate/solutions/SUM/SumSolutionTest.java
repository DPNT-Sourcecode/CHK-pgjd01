package io.accelerate.solutions.SUM;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SumSolutionTest {
    private SumSolution sum;

    @BeforeEach
    public void setUp() {
        sum = new SumSolution();
    }

    @Test
    public void compute_sum() {
        assertThat(sum.compute(1, 1), equalTo(2));
        assertThat(sum.compute(25, 75), equalTo(100));
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenArgOutOfBounds() {
        assertThrows(
                IllegalArgumentException.class,
                () -> sum.compute(50, 101),
                "Arguments must be between 0 and 100"
        );
    }
}


