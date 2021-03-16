package blackjack.domain.card.strategy;

import blackjack.domain.card.Card;

import java.util.Collections;
import java.util.List;

public class RandomShuffleStrategy implements ShuffleStrategy {
    @Override
    public void shuffle(List<Card> cards) {
        Collections.shuffle(cards);
    }
}
