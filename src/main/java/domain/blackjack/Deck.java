package domain.blackjack;

import domain.card.Card;

import java.util.Collections;
import java.util.List;

public class Deck {

    private final List<Card> cards;

    public Deck() {
        cards = Card.getCache();
        init();
    }

    private void init() {
        Collections.shuffle(cards);
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("덱에 더 이상 카드가 없습니다.");
        }
        return cards.remove(0);
    }

    public int getCardCount() {
        return cards.size();
    }
}
