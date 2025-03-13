package blackjack.domain.deck;

import java.util.Stack;

import blackjack.domain.card.Card;

@FunctionalInterface
public interface CardStrategy {

    Stack<Card> generateDeck();
}
