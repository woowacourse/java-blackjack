package blackjack.testutil;

import blackjack.domain.Card;
import blackjack.domain.Deck;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class DeckStub extends Deck {
    
    private final Deque<Card> cards;
    
    public DeckStub(List<Card> cards) {
        super();
        this.cards = new ArrayDeque<>(cards);
    }
    
    @Override
    public Card draw() {
        return cards.poll();
    }
}
