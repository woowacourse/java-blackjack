package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardPack {

    private final List<Card> cards;

    public CardPack(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card poll() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("카드를 모두 소진했습니다.");
        }
        return cards.removeFirst();
    }
}
