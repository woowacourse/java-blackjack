package blackjack.testutil;

import blackjack.domain.Card;
import blackjack.domain.deck.CardDrawer;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class CardDrawerStub implements CardDrawer {
    
    private final Deque<Card> cards;
    
    public CardDrawerStub(final List<Card> cards) {
        this.cards = new ArrayDeque<>(cards);
    }
    
    @Override
    public Card draw() {
        return cards.poll();
    }
}
