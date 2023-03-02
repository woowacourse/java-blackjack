package domain.strategy;

import java.util.Random;

public class RandomBasedIndexGenerator implements IndexGenerator {
    private static final Random RANDOM = new Random();

    @Override
    public int generate(int cardSize) {
        return RANDOM.nextInt(cardSize);
    }
}
