package blackjack.domain.card;

import java.util.Stack;

@FunctionalInterface
public interface CardGenerator {

    Stack<Card> randomGenerate();
}
