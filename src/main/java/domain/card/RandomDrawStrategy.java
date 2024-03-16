package domain.card;

import java.util.Random;

public class RandomDrawStrategy {
    
    private final Random random = new Random();

    public int generateNumber(int size) {
        return random.nextInt(size);
    }
}
