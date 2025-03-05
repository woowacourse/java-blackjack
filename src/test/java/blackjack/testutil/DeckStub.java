package blackjack.testutil;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import blackjack.domain.Card;
import blackjack.domain.Deck;

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
