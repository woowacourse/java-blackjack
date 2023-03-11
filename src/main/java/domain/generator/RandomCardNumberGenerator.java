package domain.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomCardNumberGenerator implements CardNumberGenerator {

    private static int MAXIMUM_SIZE = 52;
    private final Random random = new Random();

    private static final List<Integer> indexes = new ArrayList<>();

    static {
        for (int i = 0; i < MAXIMUM_SIZE; i++) {
            indexes.add(i);
        }
    }

    @Override
    public int generateIndex() {
        int randomNum = random.nextInt(indexes.size());
        int indexNum = indexes.get(randomNum);
        indexes.remove(randomNum);
        return indexNum;
    }
}
