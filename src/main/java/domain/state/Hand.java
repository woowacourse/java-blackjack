package domain.state;

import domain.card.Card;
import java.util.List;

public class Hand {

    private final List<Card> cards;

    public Hand(final List<Card> cards) {
        this.cards = cards;
    }
}
