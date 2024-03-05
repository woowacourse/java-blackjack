package random;

import java.util.Random;

public class RandomCardIndexShuffler implements RandomGenerator {

    @Override
    public int getRandomNumberInRange(int minNumber, int maxNumber) {
        Random random = new Random();
        return random.nextInt(minNumber, maxNumber);
    }
}
