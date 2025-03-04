package domain;

import java.util.Random;

public class RandomNumberGenerator implements NumberGenerator {
    @Override
    public int generate(int size) {
        Random random = new Random();
        return random.nextInt(size);
    }
}
