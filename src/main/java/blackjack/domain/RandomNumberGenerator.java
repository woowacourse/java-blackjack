package blackjack.domain;

import java.util.Random;

public class RandomNumberGenerator implements NumberGenerator {
    private static NumberGenerator numberGenerator = new RandomNumberGenerator();

    public static NumberGenerator getInstance() {
        return numberGenerator;
    }

    @Override
    public int generateNumber() {
        Random random = new Random();
        return random.nextInt(48);
    }
}
