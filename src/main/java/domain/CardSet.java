package domain;

import java.util.ArrayList;
import java.util.List;

public class CardSet {
    private final List<Card> cards;

    public CardSet(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }
}
