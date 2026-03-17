package domain;

import blackjack.domain.card.Card;
import blackjack.strategy.ShuffleStrategy;
import java.util.List;

public class NoShuffleStrategy implements ShuffleStrategy {

    @Override
    public void shuffle(List<Card> deck) {
    }
}
