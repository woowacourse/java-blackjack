package blackjack.domain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Deck {

    private final Queue<Card> cards;

    public Deck(List<Card> cards) {
        this.cards = new LinkedList<>(cards);
    }

    public List<Card> draw(int count) {
        List<Card> drawnCards = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            drawnCards.add(takeOutOneCard());
        }
        return drawnCards;
    }

    private Card takeOutOneCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("더 이상 꺼낼 카드가 없습니다.");
        }
        return cards.poll();
    }
}
