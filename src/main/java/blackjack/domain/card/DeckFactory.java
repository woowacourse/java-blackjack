package blackjack.domain.card;

import java.util.Stack;

public interface DeckFactory {

    Stack<Card> generate();
}
