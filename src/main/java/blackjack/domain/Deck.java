package blackjack.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Deck {

    private final Stack<Card> cards;

    private Deck(final Stack<Card> cards) {
        this.cards = cards;
    }

    public static Deck init() {
        Stack<Card> cards = new Stack<>();
        for (Suit suit : Suit.values()) {
            pushCard(cards, suit);
        }
        return new Deck(cards);
    }

    private static void pushCard(final Stack<Card> cards, final Suit suit) {
        for (Denomination denomination : Denomination.values()) {
            cards.push(new Card(suit, denomination));
        }
    }

    public int size() {
        return cards.size();
    }
}
