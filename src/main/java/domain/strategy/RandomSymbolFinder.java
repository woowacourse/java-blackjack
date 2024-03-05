package domain.strategy;

import domain.Symbol;
import domain.SymbolFinder;
import java.util.Random;

public class RandomSymbolFinder implements SymbolFinder {
    private static final int MIN_NUMBER_RANGE = 0;
    private static final int MAX_NUMBER_RANGE = 3;
    private static final RandomSymbolFinder instance = new RandomSymbolFinder();

    private final Random random = new Random();

    private RandomSymbolFinder() {
    }

    public static RandomSymbolFinder getInstance() {
        return instance;
    }

    @Override
    public Symbol find() {
        int randomIndex = random.nextInt((MAX_NUMBER_RANGE - MIN_NUMBER_RANGE) + 1) + MIN_NUMBER_RANGE;
        return Symbol.findBy(randomIndex);
    }
}
