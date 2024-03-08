package blackjack.domain.card;

import java.util.Collections;
import java.util.Stack;

public class DeckShuffleFactory implements DeckFactory {

    @Override
    public Stack<Card> generate() {
        Stack<Card> deck = new Stack<>();
        for (final Number number : Number.values()) {
            generateCardsForNumber(number, deck);
        }
        Collections.shuffle(deck);
        return deck;
    }

    private static void generateCardsForNumber(final Number number, final Stack<Card> deck) {
        for (final Suit suit : Suit.values()) {
            deck.push(new Card(number, suit));
        }
    }
}
