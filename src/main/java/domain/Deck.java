package domain;

import java.util.Collections;
import java.util.Stack;

public class Deck {
    private final Stack<Card> cards;

    public Deck() {
        cards = initCards();
    }

    private Stack<Card> initCards() {
        Stack<Card> cards = new Stack<>();

        for (Suit suit : Suit.values()) {
            pushCards(cards, suit);
        }
        Collections.shuffle(cards);

        return cards;
    }

    private void pushCards(final Stack<Card> cards, final Suit suit) {
        for (Rank rank : Rank.values()) {
            cards.push(new Card(suit, rank));
        }
    }

    public Card popCard() {
        return cards.pop();
    }
}
