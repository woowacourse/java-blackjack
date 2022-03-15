package blackjack.domain.card;

import java.util.Stack;

@FunctionalInterface
public interface CardGeneratingStrategy {

    Stack<Card> generate();
}
