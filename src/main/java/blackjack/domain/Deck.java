package blackjack.domain;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class Deck implements CardHandInitializer {
    
    private final Deque<Card> cards;
    
    public Deck() {
        cards = initCards();
    }
    
    private Deque<Card> initCards() {
        final List<Card> cards = Card.createTrumpCards();
        Collections.shuffle(cards);
        return new ArrayDeque<>(cards);
    }
    
    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("카드는 총 52장입니다.");
        }
        return cards.poll();
    }
    
    @Override
    public List<Card> init() {
        return List.of(draw(), draw());
    }
}
