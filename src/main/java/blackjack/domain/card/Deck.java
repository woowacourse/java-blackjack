package blackjack.domain.card;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public final class Deck {

    private final Deque<Card> deck;

    private Deck(final Deque<Card> deck) {
        this.deck = deck;
    }

    public static Deck from(final CardGenerator cardGenerator) {
        List<Card> cards = cardGenerator.shuffle();
        return new Deck(new ArrayDeque<>(cards));
    }

    public Card distributeCard() {
        return deck.removeFirst();
    }
}
