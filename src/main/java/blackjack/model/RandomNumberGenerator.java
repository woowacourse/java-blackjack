package blackjack.model;

import java.util.Random;

public class RandomNumberGenerator implements NumberGenerator {
    @Override
    public int pick() {
        return new Random().nextInt(1000);
    }
}
