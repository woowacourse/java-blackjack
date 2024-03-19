package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class ShuffledDeckFactory implements DeckFactory {

    @Override
    public Deck create() {
        final Stack<Card> deck = new Stack<>();
        for (final Rank rank : Rank.values()) {
            deck.addAll(createSuits(rank));
        }
        Collections.shuffle(deck);
        return new Deck(deck);
    }

    private List<Card> createSuits(final Rank rank) {
        final List<Card> suits = new ArrayList<>();
        for (final Suit suit : Suit.values()) {
            suits.add(Card.of(rank, suit));
        }
        return suits;
    }
}
