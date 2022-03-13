package blackjack.domain.card;

import java.util.Stack;

@FunctionalInterface
public interface DeckGenerator {

    Stack<Card> generate();
}
