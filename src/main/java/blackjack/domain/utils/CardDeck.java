package blackjack.domain.utils;

import blackjack.domain.card.Card;

import blackjack.domain.card.Suits;
import java.util.LinkedList;

public interface CardDeck {
    void assembleWithDenominations(LinkedList<Card> cardsValue, Suits suit);

    Card pop();

    boolean isEmpty();

}
