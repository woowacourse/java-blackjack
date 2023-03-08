package domain;

import java.util.Random;

public class RandomCardNumberGenerator implements CardNumberGenerator {

    private final Random random = new Random();

    private int maximumSize = 52;

    @Override
    public int generateIndex() {
        return random.nextInt(maximumSize--);
    }
}
