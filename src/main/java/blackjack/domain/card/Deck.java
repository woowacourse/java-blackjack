package blackjack.domain.card;

import java.util.*;

public final class Deck {

    private final Deque<Card> deck;

    public Deck(final CardGenerator cardGenerator) {
        deck = cardGenerator.generate();
    }

    public Card draw() {
        return deck.pop();
    }

    @Override
    public String toString() {
        return "Deck{" +
                "deck=" + deck +
                '}';
    }
}
