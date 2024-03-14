package domain;

import java.util.Collections;
import java.util.List;

public class RandomCardShuffleStrategy implements CardShuffleStrategy {

    @Override
    public List<Card> shuffle(List<Card> cards) {
        Collections.shuffle(cards);
        return cards;
    }
}
