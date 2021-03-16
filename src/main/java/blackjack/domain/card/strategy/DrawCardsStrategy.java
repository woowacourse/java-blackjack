package blackjack.domain.card.strategy;

import blackjack.domain.card.Card;

import java.util.List;
import java.util.Stack;

public interface DrawCardsStrategy {
    List<Card> drawCards(Stack<Card> cards);
}
