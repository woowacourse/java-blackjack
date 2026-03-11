package domain;

import blackjack.domain.Card;
import blackjack.strategy.ShuffleStrategy;
import java.util.List;

public class NoShuffleStrategy implements ShuffleStrategy {

    @Override
    public void shuffle(List<Card> deck) {
    }
}
