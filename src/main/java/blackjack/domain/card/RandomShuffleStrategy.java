package blackjack.domain.card;

import java.util.Collections;
import java.util.List;

public class RandomShuffleStrategy implements ShuffleStrategy {
    @Override
    public List<Card> shuffle(final List<Card> cards) {
        Collections.shuffle(cards);
        return cards;
    }
}
