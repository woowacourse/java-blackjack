package domain;

import java.util.LinkedHashSet;
import java.util.Set;

public class Deck {

    private static final Deck instance = create();
    private final Set<Card> cards;

    private Deck(Set<Card> cards) {
        this.cards = cards;
    }

    public static Deck getInstance() {
        return instance;
    }

    private static Deck create() {
        final Set<Card> cards = new LinkedHashSet<>();
        addAllCards(cards);
        return new Deck(cards);
    }

    private static void addAllCards(Set<Card> cards) {
        for (Suit suit : Suit.values()) {
            addAllNumbersInSuit(cards, suit);
        }
    }

    private static void addAllNumbersInSuit(Set<Card> cards, Suit suit) {
        for (Number number : Number.values()) {
            cards.add(new Card(suit, number));
        }
    }
}
