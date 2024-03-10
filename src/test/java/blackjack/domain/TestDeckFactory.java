package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.DeckFactory;
import blackjack.domain.card.Number;
import blackjack.domain.card.Suit;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TestDeckFactory implements DeckFactory {

    //@formatter:off
    /**
     * 카드 순서
     * Ace카드 HEART, SPADE, CLOVER, DIAMOND 순서대로 4장
     * 2카드 HEART, SPADE, CLOVER, DIAMOND 순서대로 4장
     * ...
     * KING카드 HEART, SPADE, CLOVER, DIAMOND 순서대로 4장
     */
    //@formatter:on
    @Override
    public Deck create() {
        final Stack<Card> deck = new Stack<>();
        for (final Number number : Number.values()) {
            deck.addAll(createSuits(number));
        }
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
