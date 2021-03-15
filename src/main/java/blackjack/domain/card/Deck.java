package blackjack.domain.card;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class Deck {

    private static final String EMPTY_DECK_ERROR_MESSAGE = "덱이 비어있습니다.";

    private final Deque<Card> deck = new ArrayDeque<Card>() {
    };

    public Deck(List<Card> cards) {
        this.deck.addAll(cards);
    }

    public Card pop() {
        if (deck.isEmpty()) {
            throw new RuntimeException(EMPTY_DECK_ERROR_MESSAGE);
        }
        return deck.pop();
    }
}
