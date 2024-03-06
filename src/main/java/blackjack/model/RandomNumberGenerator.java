package blackjack.model;

import java.util.Random;

public class RandomNumberGenerator implements NumberGenerator {
    @Override
    public int pick(int bound) {
        return new Random().nextInt(bound);
    }
}
