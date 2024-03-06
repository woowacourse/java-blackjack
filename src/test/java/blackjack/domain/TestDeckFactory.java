package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.DeckFactory;
import blackjack.domain.card.Number;
import blackjack.domain.card.Suit;
import java.util.Stack;

public class TestDeckFactory implements DeckFactory {

    /**
     * 카드 순서 :
     * Ace - HEART, SPADE, CLOVER, DIAMOND
     * 2 - HEART, SPADE, CLOVER, DIAMOND
     * ...
     * KING - HEART, SPADE, CLOVER, DIAMOND
     */
    @Override
    public Stack<Card> generate() {
        Stack<Card> deck = new Stack<>();
        for (Suit suit : Suit.values()) {
            pushNumbersOfSuit(deck, suit);
        }
        return deck;
    }

    private void pushNumbersOfSuit(Stack<Card> deck, Suit suit) {
        for (Number number : Number.values()) {
            deck.push(new Card(number, suit));
        }
    }
}
