package domain;

import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards;

    public Deck(List<Card> cards) {
        this.cards = cards;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card pop() {
        return cards.removeFirst();

        // 리스트 비었을 때 처리 필요
    }
}
