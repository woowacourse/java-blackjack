package blackjack.domain.card;

import java.util.Collections;
import java.util.Stack;

public class DeckShuffleFactory implements DeckFactory {

    @Override
    public Stack<Card> generate() {
        Stack<Card> deck = new Stack<>();
        for (Number number : Number.values()) {
            pushSuits(number, deck);
        }
        Collections.shuffle(deck);
        return deck;
    }

    private void pushSuits(final Number number, final Stack<Card> deck) {
        for (Suit suit : Suit.values()) {
            deck.push(new Card(number, suit));
        }
    }
}
