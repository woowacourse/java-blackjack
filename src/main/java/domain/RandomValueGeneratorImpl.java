package domain;

import java.util.Random;

public class RandomValueGeneratorImpl implements RandomValueGenerator {

    private final Random random = new Random();

    @Override
    public Integer generate(Integer bound) {
        return random.nextInt(bound);
    }

}
