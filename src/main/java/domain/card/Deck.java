package domain.card;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private final List<Card> cards;

    public Deck(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public Card hitCard() {
        return cards.removeFirst();
    }

    public List<Card> getCards() {
        return cards;
    }
}
