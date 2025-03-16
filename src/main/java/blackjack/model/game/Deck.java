package blackjack.model.game;

import blackjack.model.card.Card;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private final List<Card> cards;

    public Deck(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("덱에 있는 카드를 모두 사용했습니다.");
        }
        return cards.removeFirst();
    }

    public int getCardCount() {
        return cards.size();
    }
}
