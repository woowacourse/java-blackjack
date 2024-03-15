package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private final List<Card> cards;

    public Deck(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public Card draw() {
        validateNotEmpty();
        return cards.remove(0);
    }

    public void shuffle() {
        validateNotEmpty();
        Collections.shuffle(cards);
    }

    private void validateNotEmpty() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("카드가 없습니다. 카드를 추가하세요.");
        }
    }
}
