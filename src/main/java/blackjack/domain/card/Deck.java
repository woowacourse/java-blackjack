package blackjack.domain.card;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class Deck {
    private final Deque<Card> cards;

    public Deck() {
        final List<Card> newCards = CardsCache.getAllCards();
        Collections.shuffle(newCards);
        this.cards = new ArrayDeque<>(newCards);
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("덱이 비어 있을 때 카드를 뽑을 수 없습니다.");
        }
        return cards.pollFirst();
    }
}
