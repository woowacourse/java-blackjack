package blackjack.domain.card;

import java.util.Collections;
import java.util.Stack;

public final class Deck {

    private static final String INVALID_DRAW_MESSAGE = "덱이 비어있습니다.";

    private final Stack<Card> cards;

    public Deck(Stack<Card> cards) {
        this.cards = (Stack<Card>) cards.clone();
        Collections.shuffle(this.cards);
    }

    public Card draw() {
        validateIsEmpty();
        return cards.pop();
    }

    private void validateIsEmpty() {
        if (isEmpty()) {
            throw new IllegalArgumentException(INVALID_DRAW_MESSAGE);
        }
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
