package blackjack;

import java.util.Queue;

public class Deck {

    private final Queue<Card> cards;

    public Deck(Queue<Card> cards) {
        this.cards = cards;
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("더이상 뽑을 카드가 없습니다.");
        }
        return cards.poll();
    }

    public int size() {
        return cards.size();
    }
}
