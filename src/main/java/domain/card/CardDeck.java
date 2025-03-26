package domain.card;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Objects;

public class CardDeck {
    private final Deque<Card> deck;

    public CardDeck(List<Card> shuffledCards) {
        this.deck = new ArrayDeque<>(shuffledCards);
    }

    public Card getAndRemoveFrontCard() {
        return deck.removeFirst();
    }

    public Deque<Card> getDeck() {
        return deck;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CardDeck cardDeck = (CardDeck) o;
        return Objects.equals(deck, cardDeck.deck);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(deck);
    }
}
