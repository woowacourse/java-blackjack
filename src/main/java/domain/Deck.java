package domain;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private final List<Card> cards;

    public Deck(List<Card> cards) {
        this.cards = cards;
    }

    public int size() {
        return cards.size();
    }

    private Card drawCard() {
        validateEmpty();
        return cards.removeLast();
    }

    private void validateEmpty() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("덱에 남은 카드가 없습니다.");
        }
    }

    public List<Card> drawCards(int count) {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            cards.add(drawCard());
        }

        return cards;
    }
}
