package blackjack.domain;

import java.util.Collections;
import java.util.Stack;

public class Deck {
    private final Stack<Card> cards;

    public Deck(Stack<Card> cards) {
        this.cards = (Stack<Card>) cards.clone();
        Collections.shuffle(this.cards);
    }

    public Card draw() {
        validateDeckIsEmpty();
        return cards.pop();
    }

    private void validateDeckIsEmpty() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("더 이상 카드를 뽑을 수 없습니다.");
        }
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
