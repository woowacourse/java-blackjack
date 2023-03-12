package blackjack.domain.cardPicker;

import java.util.Random;

public class RandomCardPicker implements CardPicker {
    private static final Random RANDOM = new Random();

    @Override
    public int pickIndex(final int size) {
        return RANDOM.nextInt(size);
    }
}
