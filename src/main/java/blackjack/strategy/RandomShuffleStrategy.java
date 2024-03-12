package blackjack.strategy;

import java.util.Collections;
import java.util.List;

public class RandomShuffleStrategy implements ShuffleStrategy {

    @Override
    public void shuffle(final List<?> list) {
        Collections.shuffle(list);
    }
}
