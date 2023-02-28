package blackjack.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cards {
    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return this.cards;
    }
}
