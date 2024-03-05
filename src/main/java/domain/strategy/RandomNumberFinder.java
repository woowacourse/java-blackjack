package domain.strategy;

import domain.Number;
import domain.NumberFinder;
import java.util.Random;

public class RandomNumberFinder implements NumberFinder {
    private static final int MIN_NUMBER_RANGE = 0;
    private static final int MAX_NUMBER_RANGE = 11;
    private static final RandomNumberFinder instance = new RandomNumberFinder();

    private final Random random = new Random();

    private RandomNumberFinder() {
    }

    public static RandomNumberFinder getInstance() {
        return instance;
    }

    @Override
    public Number find() {
        int randomIndex = random.nextInt((MAX_NUMBER_RANGE - MIN_NUMBER_RANGE) + 1) + MIN_NUMBER_RANGE;
        return Number.findBy(randomIndex);
    }
}
