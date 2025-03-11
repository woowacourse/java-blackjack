package blackjack.domain.card;

import java.util.Stack;

@FunctionalInterface
public interface CardsShuffler {
    void shuffleCards(Stack<Card> cards);
}
