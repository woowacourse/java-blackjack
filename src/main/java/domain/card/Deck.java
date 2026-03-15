package domain.card;

import java.util.Collections;
import java.util.List;

public record Deck(List<Card> cards) {
    public Deck(List<Card> cards) {
        this.cards = cards;
        shuffle();
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("남은 카드가 없습니다.");
        }
        return cards.removeFirst();
    }

    private void shuffle() {
        Collections.shuffle(cards);
    }
}
