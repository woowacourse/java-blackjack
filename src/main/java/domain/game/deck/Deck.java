package domain.game.deck;

import domain.card.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        Collections.shuffle(cards);
    }

    public Deck(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public Card pick() {
        return cards.remove(0);
    }

    public int getTotalSize() {
        return cards.size();
    }
}
