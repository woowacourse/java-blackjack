package domain;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;

import java.util.*;


public class Deck {
    private Stack<Card> cards = new Stack<>();

    private Deck(Stack<Card> cards) {
        this.cards = cards;
    }

    public static Deck of() {
        Stack<Card> cards = new Stack<>();

        prepareCards(cards);
        Collections.shuffle(List.of(cards));

        return new Deck(cards);
    }

    private static void prepareCards(Stack<Card> cards) {
        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values()) {
                Card card = new Card(rank, suit);
                cards.add(card);
            }
        }
    }

    public int size() {
        return cards.size();
    }

    public Card draw() {
        return cards.pop();
    }
}
