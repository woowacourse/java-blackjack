package blackjack.domain.card.strategy;

import blackjack.domain.card.Card;
import java.util.LinkedList;

@FunctionalInterface
public interface CardsGenerateStrategy {
    LinkedList<Card> generate();
}
