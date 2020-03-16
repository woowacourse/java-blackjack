package blackjack.domain.card;

import java.util.Stack;

public interface ProcessStrategy {
    void shuffle(Stack<Card> cards);
}