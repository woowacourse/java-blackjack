package domain;

import java.util.Collections;
import java.util.List;

public class DefaultShuffle implements ShuffleStrategy {

    @Override
    public void shuffle(List<TrumpCard> cards) {
        Collections.shuffle(cards);
    }
}
