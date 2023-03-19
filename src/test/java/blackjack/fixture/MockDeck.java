package blackjack.fixture;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;

import java.util.ArrayDeque;
import java.util.List;

public class MockDeck extends Deck {
    public static MockDeck create(final List<Card> cards) {
        return new MockDeck(cards);
    }

    private MockDeck(final List<Card> cards) {
        super.cards = new ArrayDeque<>(cards);
    }

    @Override
    public Card draw() {
        return super.cards.pop();
    }
}
