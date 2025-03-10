package blackjack.domain.card;

import java.util.List;
import java.util.Stack;

@FunctionalInterface
public interface CardsShuffler {
    void shuffleCards(Stack<Card> cards) ;
}
