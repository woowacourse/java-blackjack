package domain.card;

import java.util.Stack;

public class Deck {
    private final Stack<Card> deck;

    private Deck(Stack<Card> deck) {
        this.deck = deck;
    }

    public static Deck from(ShuffleStrategy shuffleStrategy) {
        Stack<Card> deck = new Stack<>();
        for (Suit suit : Suit.values()) {
            makeCard(deck, suit);
        }
        return new Deck(shuffleStrategy.shuffle(deck));
    }

    private static void makeCard(Stack<Card> deck, Suit suit) {
        for (Rank rank : Rank.values()) {
            deck.add(new Card(suit, rank));
        }
    }

    public Card draw() {
        return deck.pop();
    }
}
