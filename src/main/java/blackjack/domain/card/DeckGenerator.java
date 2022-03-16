package blackjack.domain.card;

import java.util.Stack;

public interface DeckGenerator {
    Stack<Card> generate();
}
