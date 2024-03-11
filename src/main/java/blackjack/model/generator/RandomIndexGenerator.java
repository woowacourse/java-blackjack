package blackjack.model.generator;

import java.util.Random;

public class RandomIndexGenerator implements IndexGenerator {
    private final Random random = new Random();

    @Override
    public int generate(int maxRange) {
        return random.nextInt(maxRange) + 1;
    }
}
