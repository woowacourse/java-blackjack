package blackjack.domain;

import java.util.Stack;

public class DeckFactory {

    public static Deck createDefaultDeck() {
        Stack<Card> deck = new Stack<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                deck.add(new Card(suit, rank));
            }
        }
        return new Deck(deck);
    }
}
