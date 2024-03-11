package blackjack.domain.stategy;

import blackjack.domain.card.Card;
import blackjack.strategy.ShuffleStrategy;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NoShuffleStrategy implements ShuffleStrategy {

    @Override
    public Queue<Card> shuffle(final List<Card> cards) {
        return new LinkedList<>(cards);
    }
}
