package blackjack.util;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class FixedDeck implements Deck {

    private final Deque<Card> cards;

    public FixedDeck(final List<Card> cards) {
        this.cards = new ArrayDeque<>(cards);
    }

    @Override
    public Card draw() {
        return cards.removeFirst();
    }
}
