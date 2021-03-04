package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {
    private static final int INITIAL_CARDS_SIZE = 2;
    private final List<Card> cards;

    public Cards(List<Card> cards) {
        if (cards.size() != INITIAL_CARDS_SIZE) {
            throw new IllegalArgumentException();
        }
        this.cards = new ArrayList<>(cards);
    }

    public List<Card> getUnmodifiableList() {
        return Collections.unmodifiableList(cards);
    }

    public void add(Card card) {
        cards.add(card);
    }
}