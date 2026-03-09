package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {
    private final List<Card> cards;

    private Deck(List<Card> cards) {
        this.cards = cards;
    }

    public static Deck create() {
        List<Card> cards = new ArrayList<>();

        for (Suit suit : Suit.values()) {
            for (CardNumber cardNumber : CardNumber.values()) {
                cards.add(new Card(suit, cardNumber));
            }
        }

        return new Deck(cards);
    }

    public Card pop() {
        Card card = cards.getLast();
        cards.removeLast();

        return card;
    }

    public void shuffle() {
        Collections.shuffle(this.cards);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(this.cards);
    }
}
