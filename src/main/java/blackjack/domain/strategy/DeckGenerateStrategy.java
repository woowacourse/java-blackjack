package blackjack.domain.strategy;

import blackjack.domain.card.Card;
import java.util.Deque;

@FunctionalInterface
public interface DeckGenerateStrategy {
    Deque<Card> generate();
}
