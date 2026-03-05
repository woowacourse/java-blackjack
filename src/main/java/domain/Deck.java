package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards;

    private Deck(List<Card> cards) {
        this.cards = cards;
    }

    public static Deck createShuffleDeck() {
        List<Card> cards = new ArrayList<>();

        for (Suit suit : Suit.values()) {
            for (CardNumber cardNumber : CardNumber.values()) {
                cards.add(new Card(suit, cardNumber));
            }
        }

        Collections.shuffle(cards);
        return new Deck(cards);
    }
}
