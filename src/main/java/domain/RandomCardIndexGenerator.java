package domain;

import java.util.Random;

public class RandomCardIndexGenerator implements CardIndexGenerator {

    private final Random random = new Random();

    @Override
    public int chooseIndex(final int deckSize) {
        return random.nextInt(deckSize);
    }
}
