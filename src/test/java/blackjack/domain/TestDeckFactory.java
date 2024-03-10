package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.DeckFactory;
import blackjack.domain.card.Number;
import blackjack.domain.card.Suit;
import java.util.Stack;

public class TestDeckFactory implements DeckFactory {

    /**
     * 카드 순서 : Ace - HEART, SPADE, CLOVER, DIAMOND 2 - HEART, SPADE, CLOVER, DIAMOND ... KING - HEART, SPADE, CLOVER,
     * DIAMOND
     */
    @Override
    public Deck create() {
        final Stack<Card> deck = new Stack<>();
        for (final Suit suit : Suit.values()) {
            pushNumbersOfSuit(deck, suit);
        }
        return new Deck(deck);
    }

    private void pushNumbersOfSuit(final Stack<Card> deck, final Suit suit) {
        for (final Number number : Number.values()) {
            deck.push(new Card(number, suit));
        }
    }
}
