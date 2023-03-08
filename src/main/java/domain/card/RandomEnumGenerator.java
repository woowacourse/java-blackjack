package domain.card;

import java.util.Random;

public class RandomEnumGenerator<T extends Enum<T>> {
    private static final Random random = new Random();
    private final T[] values;

    public RandomEnumGenerator(Class<T> e) {
        values = e.getEnumConstants();
    }

    public T randomEnum() {
        return values[random.nextInt(values.length)];
    }
}
