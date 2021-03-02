package blackjack;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private final List<Card> cards;

    public Cards(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public List<Card> getList() {
        return new ArrayList<>(cards);
    }
}
