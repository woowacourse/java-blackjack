package blackjack.domain.generator;

import java.util.Random;

public class RandomNumberGenerator implements NumberGenerator {

    private static final Random random = new Random();

    @Override
    public int generate(final int maxRange) {
        return random.nextInt(maxRange);
    }
}
