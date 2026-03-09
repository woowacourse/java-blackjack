package domain.card;

import java.util.Collections;
import java.util.List;

public class DefaultShuffleStrategy implements ShuffleStrategy {
    @Override
    public void shuffle(List<Card> cards) {
        Collections.shuffle(cards);
    }
}
