package domain.card;

import domain.card.Card;
import java.util.Deque;

public class Deck {
    private static final String DECK_IS_EMPTY = "덱이 비어있습니다.";

    private final Deque<Card> deck;

    public Deck(Deque<Card> deck) {
        this.deck = deck;
    }

    public Card drawCard() {
        validateIsNotEmpty();

        return deck.pop();
    }

    private void validateIsNotEmpty() {
        if (deck.isEmpty()) {
            throw new IllegalStateException(DECK_IS_EMPTY);
        }
    }

    public int getSize() {
        return this.deck.size();
    }
}
