package blackjack.domain.deck;

import blackjack.domain.card.Card;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public final class Deck implements CardDrawer {

    private final Deque<Card> cards;
    
    public Deck() {
        this.cards = initCards();
    }
    
    private Deque<Card> initCards() {
        final List<Card> cards = Card.createTrumpCards();
        Collections.shuffle(cards);
        return new ArrayDeque<>(cards);
    }
    
    @Override
    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("카드는 총 52장입니다.");
        }
        return cards.poll();
    }
}
