package blackjack.strategy;

import blackjack.domain.card.Card;
import java.util.List;
import java.util.Queue;

public interface ShuffleStrategy {

    Queue<Card> shuffle(List<Card> cards);
}
