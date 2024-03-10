package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class DeckShuffleFactory implements DeckFactory {

    @Override
    public Deck create() {
        final Stack<Card> deck = new Stack<>();
        for (final Number number : Number.values()) {
            deck.addAll(createSuits(number));
        }
        Collections.shuffle(deck);
        return new Deck(deck);
    }

    private List<Card> createSuits(final Number number) {
        final List<Card> suits = new ArrayList<>();
        for (final Suit suit : Suit.values()) {
            suits.add(new Card(number, suit));
        }
        return suits;
    }
}
