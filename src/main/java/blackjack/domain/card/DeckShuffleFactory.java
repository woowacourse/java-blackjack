package blackjack.domain.card;

import java.util.Collections;
import java.util.Stack;

public class DeckShuffleFactory implements DeckFactory {

    @Override
    public Stack<Card> generate() {
        Stack<Card> deck = new Stack<>();
        for (Number number : Number.values()) {
            generateCardsForNumber(number, deck);
        }
        Collections.shuffle(deck);
        return deck;
    }

    private static void generateCardsForNumber(Number number, Stack<Card> deck) {
        for (Suit suit : Suit.values()) {
            deck.push(new Card(number, suit));
        }
    }
}
