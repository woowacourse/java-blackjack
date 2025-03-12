package domain;

import java.util.Collections;
import java.util.List;

public class CardShuffler implements ShuffleStrategy {

    @Override
    public void shuffle(final List<Card> cards) {
        Collections.shuffle(cards);
    }
}
