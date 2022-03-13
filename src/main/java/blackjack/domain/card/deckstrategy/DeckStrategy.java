package blackjack.domain.card.deckstrategy;

import java.util.Stack;

import blackjack.domain.card.Card;

public interface DeckStrategy {
    Stack<Card> create();
}
