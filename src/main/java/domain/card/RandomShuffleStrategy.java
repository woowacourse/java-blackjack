package domain.card;

import java.util.Collections;
import java.util.List;

public class RandomShuffleStrategy implements ShuffleStrategy {
    @Override
    public void shuffle(List<Card> deck) {
        Collections.shuffle(deck);
    }
}
