package blackjack.domain.card;

import java.util.Stack;

public class TestDeckFactory implements DeckFactory {

    private final Stack<Card> deck;

    public TestDeckFactory(final Stack<Card> cards) {
        this.deck = cards;
    }

    @Override
    public Stack<Card> generate() {
        return deck;
    }
}
