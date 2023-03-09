package blackjack.util;

import java.util.Random;

public class RandomCardPickerGenerator implements CardPickerGenerator {

    Random random = new Random();

    @Override
    public int generator(final int cardSize) {
        return random.nextInt(cardSize);
    }
}
