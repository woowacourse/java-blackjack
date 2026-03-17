package blackjack.domain.strategy;

import blackjack.domain.card.Card;
import blackjack.strategy.ShuffleStrategy;
import java.util.Collections;
import java.util.List;

public class RandomShuffleStrategy implements ShuffleStrategy {
    @Override
    public void shuffle(List<Card> deck) {
        Collections.shuffle(deck);
    }
}
