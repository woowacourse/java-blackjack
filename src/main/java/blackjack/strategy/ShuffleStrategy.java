package blackjack.strategy;

import blackjack.domain.Card;
import java.util.List;

@FunctionalInterface
public interface ShuffleStrategy {
    void shuffle(List<Card> deck);
}
