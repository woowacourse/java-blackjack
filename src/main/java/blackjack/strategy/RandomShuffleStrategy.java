package blackjack.strategy;

import blackjack.domain.card.Card;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RandomShuffleStrategy implements ShuffleStrategy {

    @Override
    public Queue<Card> shuffle(final List<Card> cards) {
        Collections.shuffle(cards);
        return new LinkedList<>(cards);
    }
}
