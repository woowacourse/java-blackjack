package util;

import java.security.SecureRandom;
import java.util.Random;

public class RandomPicker implements PickerStrategy {

    private final Random random = new SecureRandom();

    @Override
    public int next(int upperBoundExclusive) {
        return random.nextInt(upperBoundExclusive);
    }
}
