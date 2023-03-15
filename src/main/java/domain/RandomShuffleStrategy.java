package domain;

import java.util.Collections;
import java.util.List;

public class RandomShuffleStrategy implements ShuffleStrategy {
    @Override
    public void shuffle(final List<Card> deck) {
        Collections.shuffle(deck);
    }
}
