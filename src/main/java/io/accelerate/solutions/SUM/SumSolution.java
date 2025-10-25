package io.accelerate.solutions.SUM;

@SuppressWarnings("unused")
public class SumSolution {

    public int compute(int x, int y) {
        if (x < 0 || x > 100 || y < 0 || y > 100) {
            throw new IllegalArgumentException("Arguments must be between 0 and 100");
        }
        return x + y;
    }
}
