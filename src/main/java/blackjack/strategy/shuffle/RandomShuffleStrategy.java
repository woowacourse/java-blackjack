package blackjack.strategy.shuffle;

import java.util.Collections;
import java.util.List;

public class RandomShuffleStrategy implements ShuffleStrategy {

    @Override
    public void shuffle(final List<?> list) {
        Collections.shuffle(list);
    }
}
