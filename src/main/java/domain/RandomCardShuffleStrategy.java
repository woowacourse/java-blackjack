package domain;

import java.util.Collections;
import java.util.List;

public class RandomCardShuffleStrategy implements CardShuffleStrategy {
    @Override
    public void shuffle(List<Card> cards) {
        Collections.shuffle(cards);
    }
}
