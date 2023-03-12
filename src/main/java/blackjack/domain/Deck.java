package blackjack.domain;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class Deck {
    private final Deque<Card> cards;

    private Deck(Deque<Card> cards) {
        this.cards = cards;
    }

    public static Deck of(List<Card> cards) {
        return new Deck(new ArrayDeque<>(cards));
    }

    public Card draw() {
        validateDeckIsEmpty();
        return cards.remove();
    }

    private void validateDeckIsEmpty() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("더 이상 카드를 뽑을 수 없습니다.");
        }
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public int getSize() {
        return cards.size();
    }
}
