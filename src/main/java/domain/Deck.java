package domain;

import java.util.ArrayDeque;
import java.util.Deque;

public class Deck {

    private static final Deck instance = create();
    private final Deque<Card> cards;

    private Deck(Deque<Card> cards) {
        this.cards = cards;
    }

    public static Deck getInstance() {
        return instance;
    }

    private static Deck create() {
        final Deque<Card> cards = new ArrayDeque<>();
        addAllCards(cards);
        return new Deck(cards);
    }

    private static void addAllCards(Deque<Card> cards) {
        for (Suit suit : Suit.values()) {
            addAllNumbersInSuit(cards, suit);
        }
    }

    private static void addAllNumbersInSuit(Deque<Card> cards, Suit suit) {
        for (Number number : Number.values()) {
            cards.addLast(new Card(suit, number));
        }
    }

    public Card draw() {
        return cards.pollLast();
    }
}
