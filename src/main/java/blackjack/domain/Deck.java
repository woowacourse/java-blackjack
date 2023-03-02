package blackjack.domain;

import java.util.Collections;
import java.util.Stack;

public class Deck {

    private final Stack<Card> cards;

    public Deck(final Stack<Card> cards) {
        this.cards = cards;
    }

    public Card draw() {
        if (cards.empty()) {
            throw new IllegalStateException("덱에 더 이상의 카드가 없습니다.");
        }
        return cards.pop();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }
}
