package io.accelerate.solutions.SUM;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


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

    @ParameterizedTest
    @ValueSource(ints = {-1, 101})
    public void shouldThrowIllegalArgumentExceptionWhenXArgOutOfBounds(int outOfBoundsArg) {
        assertThrows(
                IllegalArgumentException.class,
                () -> sum.compute(outOfBoundsArg, 50),
                "Arguments must be between 0 and 100"
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 101})
    public void shouldThrowIllegalArgumentExceptionWhenYArgOutOfBounds(int outOfBoundsArg) {
        assertThrows(
                IllegalArgumentException.class,
                () -> sum.compute(50, outOfBoundsArg),
                "Arguments must be between 0 and 100"
        );
    }
}
