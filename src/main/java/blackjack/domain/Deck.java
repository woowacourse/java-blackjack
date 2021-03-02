package blackjack.domain;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Deck {
    private final Queue<Card> cards;

    public Deck(List<Card> cards) {
        this.cards = new LinkedList<>(cards);
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }

    public Card draw() {
        Card card = cards.poll();
        if (card == null) {
            throw new IllegalStateException("덱이 모두 소진되었습니다.");
        }
        return card;
    }
}
