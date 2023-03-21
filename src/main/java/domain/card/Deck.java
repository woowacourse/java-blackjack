package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards;

    private Deck(List<Card> cards) {
        this.cards = cards;
    }

    public static Deck create() {
        List<Card> cards = initializeCards();
        Collections.shuffle(cards);
        return new Deck(cards);
    }

    private static List<Card> initializeCards() {
        List<Card> cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            initializeCardsByShape(cards, suit);
        }

        return cards;
    }

    private static void initializeCardsByShape(List<Card> cards, Suit suit) {
        for (Denomination denomination : Denomination.values()) {
            cards.add(new Card(suit, denomination));
        }
    }

    public Card findAnyOneCard() {
        return cards.remove(0);
    }
}
