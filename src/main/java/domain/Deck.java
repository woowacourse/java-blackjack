package domain;

import java.util.Collections;
import java.util.Stack;

public class Deck {
    private static final Stack<Card> cards = initCards();

    private Deck() {
    }

    private static Stack<Card> initCards() {
        Stack<Card> cards = new Stack<>();

        for (Suit suit : Suit.values()) {
            pushCards(cards, suit);
        }
        Collections.shuffle(cards);

        return cards;
    }

    private static void pushCards(final Stack<Card> cards, final Suit suit) {
        for (Rank rank : Rank.values()) {
            cards.push(new Card(suit, rank));
        }
    }

    public static Card popCard() {
        return cards.pop();
    }
}
