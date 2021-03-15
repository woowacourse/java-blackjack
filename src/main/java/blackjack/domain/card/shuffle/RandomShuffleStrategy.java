package blackjack.domain.card.shuffle;

import blackjack.domain.card.Card;

import java.util.Collections;
import java.util.List;

public class RandomShuffleStrategy implements ShuffleStrategy {
    @Override
    public List<Card> shuffle(final List<Card> cards) {
        Collections.shuffle(cards);
        return cards;
    }
}
