package blackjack.service;

import java.util.Random;

public class RandomNumberGenerator implements NumberGenerator {

    Random random = new Random();

    @Override
    public int generate(int max) {
        return random.nextInt(max);
    }
}
