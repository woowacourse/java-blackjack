package blackjack.domain;

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

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("덱에 카드가 존재하지 않습니다.");
        }
        return cards.remove(cards.size() - 1);
    }
}
