package blackjack.domain.card;

import java.util.Collections;
import java.util.Stack;

public class DeckShuffleFactory implements DeckFactory {

    @Override
    public Deck create() {
        final Stack<Card> deck = new Stack<>();
        for (final Number number : Number.values()) {
            pushSuits(number, deck);
        }
        Collections.shuffle(deck);
        return new Deck(deck);
    }

    private void pushSuits(final Number number, final Stack<Card> deck) {
        for (final Suit suit : Suit.values()) {
            deck.push(new Card(number, suit));
        }
    }
}
