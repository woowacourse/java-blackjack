package domain;

import java.util.Random;

public class RandomNumberGeneartor implements NumberGenerator {

    private final int minRange;
    private final int maxRange;

    public RandomNumberGeneartor(final int minRange, final int maxRange) {
        this.minRange = minRange;
        this.maxRange = maxRange;
    }

    @Override
    public int generate() {
        return new Random().nextInt(minRange, maxRange);
    }
}
