package domain.card;

import java.util.Random;

public class RandomDrawStrategy implements DrawStrategy {

    @Override
    public int generateNumber(int size) {
        return new Random().nextInt(size) + 1;
    }
}
