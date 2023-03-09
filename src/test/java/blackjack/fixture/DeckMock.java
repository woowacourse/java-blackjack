package blackjack.fixture;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;

import java.util.ArrayDeque;
import java.util.List;

public class DeckMock extends Deck {
    public static DeckMock create(final List<Card> cards) {
        return new DeckMock(cards);
    }

    private DeckMock(final List<Card> cards) {
        super.cards = new ArrayDeque<>(cards);
    }

    @Override
    public Card draw() {
        return super.cards.pollFirst();
    }
}
