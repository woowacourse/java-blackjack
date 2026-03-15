package domain.card;

import exception.BlackjackException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards;

    public Deck(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public int getSize() {
        return cards.size();
    }

    public Card pop() {
        if (cards.isEmpty()) {
            throw new BlackjackException("덱이 비었습니다.");
        }

        return cards.removeFirst();
    }
}
