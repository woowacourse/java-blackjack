package domain.game;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        create();
        Collections.shuffle(cards);
    }

    public Deck(final List<Card> cards) {
        this.cards = new ArrayList<>();
        for (Card card : cards) {
            this.cards.add(card);
        }
    }

    private void create() {
        for (Suit suit : Suit.values()) {
            addCardsOfShape(suit);
        }
    }

    private void addCardsOfShape(final Suit suit) {
        for (Denomination denomination : Denomination.values()) {
            cards.add(new Card(denomination, suit));
        }
    }

    public Card pick() {
        return cards.remove(0);
    }

    public int getTotalSize() {
        return cards.size();
    }
}
