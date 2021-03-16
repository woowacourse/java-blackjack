package blackjack.domain.card;

import java.util.List;
import java.util.Stack;

public interface DrawCardsStrategy {
    List<Card> drawCards(Stack<Card> cards);
}
